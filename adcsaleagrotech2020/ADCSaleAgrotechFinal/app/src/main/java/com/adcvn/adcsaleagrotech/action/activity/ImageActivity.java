package com.adcvn.adcsaleagrotech.action.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.activity.camerasuface.CameraActivity;
import com.adcvn.adcsaleagrotech.adapter.images.ImageAdapter;
import com.adcvn.adcsaleagrotech.model.transactionphoto.ImageModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static com.adcvn.adcsaleagrotech.common.SystemImage.getImageUri;
import static com.adcvn.adcsaleagrotech.common.SystemImage.rotateBitmap;

public class ImageActivity extends AppCompatActivity {
    private ImageButton ibClose, ibCamera;
    private RecyclerView rvImage;
    private ImageAdapter imageAdapter;
    private List<ImageModel> transactionPhotos = new ArrayList<>();
    // Khai báo biến tạm
    private final int LAUNCH_CAMERA_ACTIVITY = 1;
    // khai báo thư viện bên ngoài
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FragmentManager fm;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        context = this;
        fm = getSupportFragmentManager();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        initView();
        initRecyclerView();
        initEvent();
        initData();
    }

    private void initView() {
//        Image button đóng màn hình
        ibClose = findViewById(R.id.ibClose);
//        Image button thực hiện chức năng chụp hình
        ibCamera = findViewById(R.id.ibCamera);
//        RecyclerView danh sách ảnh
        rvImage = findViewById(R.id.rvImages);
    }


    private void initData() {

    }

    private void initRecyclerView() {
        ImageModel model = new ImageModel("Demo User", "02/04/2020 15:39");
        for (int i = 0; i < 5; i++) {
            transactionPhotos.add(model);
        }
        LinearLayoutManager linearLayoutRecycleView = new LinearLayoutManager(this);
        rvImage.setLayoutManager(linearLayoutRecycleView);
        imageAdapter = new ImageAdapter(context, transactionPhotos);
        rvImage.setAdapter(imageAdapter);
    }

    // khởi tạo sự kiện giao diện màn hình
    private void initEvent() {

        //Image chụp ảnh
        ibCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImageActivity.this, CameraActivity.class);
                startActivityForResult(intent, LAUNCH_CAMERA_ACTIVITY);
            }
        });
        // ImageButton thoát
        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_CAMERA_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                String pathImage = data.getStringExtra("pathImage");
                Uri uri = Uri.fromFile(new File(pathImage));
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bitmap = rotateBitmap(bitmap,90);
                uri = getImageUri(context,bitmap);
                uploadImageCapture(uri);
            }
        }
    }

    //Xử lý cập nhật hình ảnh
    private void uploadImageCapture(Uri uriPhotoFile) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();
        final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
        ref.putFile(uriPhotoFile)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String photoLink = uri.toString();
                                progressDialog.dismiss();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                .getTotalByteCount());
                        progressDialog.setMessage("" + (int) progress + "%");
                    }
                });
    }
}