package com.adcvn.adcsaleagrotech.server;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.adcvn.adcsaleagrotech.R;
import com.adcvn.adcsaleagrotech.action.model.error.ApiErrorResult;
import com.adcvn.adcsaleagrotech.common.Constant;
import com.adcvn.adcsaleagrotech.common.SystemDialog;
import com.adcvn.adcsaleagrotech.common.SystemError;
import com.adcvn.adcsaleagrotech.dialog.ErrorMessageDialog;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IARBankList;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IAppLogin;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IDepositedAmountAction;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.ILogin;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IMisaCustomerDebtCheck;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IOMCustomerList;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IOMEmployeeList;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IOMOrderTypeList;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IOMSalesOrderList;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IPriceListGet;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IReceiptRequestAction;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IReceiptedAction;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IReceiptedTransferCustomerAction;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IReceivableList;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.ISalesOrder;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.ISalesOrderAction;
import com.adcvn.adcsaleagrotech.middleinterface.apimiddleinterface.IVerifyOtp;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginDetail;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginPost;
import com.adcvn.adcsaleagrotech.model.applogin.AppLoginResult;
import com.adcvn.adcsaleagrotech.model.arbanklist.ARBankListResult;
import com.adcvn.adcsaleagrotech.model.createorder.SalesOrderActionDetail;
import com.adcvn.adcsaleagrotech.model.createorder.SalesOrderActionPost;
import com.adcvn.adcsaleagrotech.model.createorder.SalesOrderActionResult;
import com.adcvn.adcsaleagrotech.model.depositedamountaction.DepositedAmountActionDetail;
import com.adcvn.adcsaleagrotech.model.depositedamountaction.DepositedAmountActionPost;
import com.adcvn.adcsaleagrotech.model.depositedamountaction.DepositedAmountActionResult;
import com.adcvn.adcsaleagrotech.model.login.LoginDetail;
import com.adcvn.adcsaleagrotech.model.login.LoginPost;
import com.adcvn.adcsaleagrotech.model.login.LoginResult;
import com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck.MisaCustomerDebtCheck;
import com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck.MisaCustomerDebtCheckPost;
import com.adcvn.adcsaleagrotech.model.misacustomerdebtcheck.MisaCustomerDebtCheckResult;
import com.adcvn.adcsaleagrotech.model.omcustomerlist.OMCustomerListResult;
import com.adcvn.adcsaleagrotech.model.omemployeelist.OMEmployeeListResult;
import com.adcvn.adcsaleagrotech.model.omsalesorderlist.OMSalesOrderListResult;
import com.adcvn.adcsaleagrotech.model.ordertypelist.OMOrderTypeListResult;
import com.adcvn.adcsaleagrotech.model.pricelistget.PriceListGet;
import com.adcvn.adcsaleagrotech.model.pricelistget.PriceListGetPost;
import com.adcvn.adcsaleagrotech.model.pricelistget.PriceListGetResult;
import com.adcvn.adcsaleagrotech.model.receiptedtransfercustomeraction.ReceiptedTransferCustomerActionDetail;
import com.adcvn.adcsaleagrotech.model.receiptedtransfercustomeraction.ReceiptedTransferCustomerActionPost;
import com.adcvn.adcsaleagrotech.model.receiptedtransfercustomeraction.ReceiptedTransferCustomerActionResult;
import com.adcvn.adcsaleagrotech.model.receiptrequestaction.ReceiptRequestActionDetail;
import com.adcvn.adcsaleagrotech.model.receiptrequestaction.ReceiptRequestActionPost;
import com.adcvn.adcsaleagrotech.model.receiptrequestaction.ReceiptRequestActionResult;
import com.adcvn.adcsaleagrotech.model.receivableaction.ReceiptedActionDetail;
import com.adcvn.adcsaleagrotech.model.receivableaction.ReceiptedActionPost;
import com.adcvn.adcsaleagrotech.model.receivableaction.ReceiptedActionResult;
import com.adcvn.adcsaleagrotech.model.receivemoney.receivablelist.ReceivableListResult;
import com.adcvn.adcsaleagrotech.model.salesorder.SalesOrder;
import com.adcvn.adcsaleagrotech.model.verifyopt.VerifyOtpPost;
import com.adcvn.adcsaleagrotech.model.verifyopt.VerifyOtpResult;
import com.adcvn.adcsaleagrotech.model.verifyopt.VerifyOtpResultDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CenterCallApi {
    private Context context;
    private static HttpClient httpClient;

    public CenterCallApi(Context context) {
        this.context = context;
        if (httpClient == null) {
            try {
                httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // api đăng nhập hệ thống
    public void login(final ILogin iLogin, final LoginPost loginPost, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            Call<LoginResult> callback = httpClient.login(loginPost);
            callback.enqueue(new Callback<LoginResult>() {
                @Override
                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            LoginResult result = response.body();
                            LoginDetail loginDetail = result.getLoginDetail().get(0);
                            if (loginDetail.getErrorCode() == 0)
                                iLogin.getLogin(result);
                            else {
                                ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                                Bundle args = new Bundle();
                                args.putString("type", Constant.TYPE_NORMAL);
                                args.putString("title", context.getResources().getString(R.string.label_title_notification));
                                args.putString("firstMessageContent", loginDetail.getMessage());
                                errorMessageDialog.setArguments(args);
                                SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        args.putString("secondMessageContent", context.getResources().getString(R.string.title_message_not_session_second_label));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<LoginResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api xác thực otp
    public void loginOTPCheck(final IVerifyOtp iVerifyOtp, final VerifyOtpPost verifyOtpPost, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            Call<VerifyOtpResult> callback = httpClient.loginOTPCheck(verifyOtpPost);
            callback.enqueue(new Callback<VerifyOtpResult>() {
                @Override
                public void onResponse(Call<VerifyOtpResult> call, Response<VerifyOtpResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            VerifyOtpResult result = response.body();
                            VerifyOtpResultDetail verifyOtpResultDetail = result.getValue().get(0);
                            if (verifyOtpResultDetail.getErrorCode() == 0)
                                iVerifyOtp.getVerifyOtpResult(result);
                            else {
                                ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                                Bundle args = new Bundle();
                                args.putString("type", Constant.TYPE_NORMAL);
                                args.putString("title", context.getResources().getString(R.string.label_title_notification));
                                args.putString("firstMessageContent", verifyOtpResultDetail.getMessage());
                                errorMessageDialog.setArguments(args);
                                SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        args.putString("secondMessageContent", context.getResources().getString(R.string.title_message_not_session_second_label));
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_error_message_login_dialog));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_error_message_login_dialog));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<VerifyOtpResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api lấy thông tin nhân viên bán hàng
    public void appLogin(final IAppLogin iAppLogin, final AppLoginPost appLoginPost, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            Call<AppLoginResult> callback = httpClient.appLogin(appLoginPost);
            callback.enqueue(new Callback<AppLoginResult>() {
                @Override
                public void onResponse(Call<AppLoginResult> call, Response<AppLoginResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            AppLoginResult result = response.body();
                            AppLoginDetail appLoginDetail = result.getAppLoginDetail().get(0);
                            if (appLoginDetail.getErrorCode() == 0)
                                iAppLogin.getAppLogin(result);
                            else {
                                ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                                Bundle args = new Bundle();
                                args.putString("type", Constant.TYPE_NORMAL);
                                args.putString("title", context.getResources().getString(R.string.label_title_notification));
                                args.putString("firstMessageContent", appLoginDetail.getMessage());
                                errorMessageDialog.setArguments(args);
                                SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        args.putString("secondMessageContent", context.getResources().getString(R.string.title_message_not_session_second_label));
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<AppLoginResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.title_error_connection));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.title_error_connection));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api danh sách phải thu
    public void receivableList(final int isCurrent, final IReceivableList iReceivableList, int skip, String search, String filter, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            String url = Constant.LINK_REQUEST + "_ARReceivableList?$top=" + Constant.MAX_PAGE_ELEMENT + "&$skip=" + skip
                    + "&$count=true&$orderby=SeqNum desc&$filter=" + filter;
            Call<ReceivableListResult> callback = httpClient.receivableList(url);
            callback.enqueue(new Callback<ReceivableListResult>() {
                @Override
                public void onResponse(Call<ReceivableListResult> call, Response<ReceivableListResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            ReceivableListResult result = response.body();
                            iReceivableList.getReceivableList(result, isCurrent);
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", e.getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<ReceivableListResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api thu tiền phiếu thu
    public void receiptedAction(final IReceiptedAction iReceiptedAction, final ReceiptedActionPost receiptedActionPost, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            Call<ReceiptedActionResult> callback = httpClient.receiptedAction(receiptedActionPost);
            callback.enqueue(new Callback<ReceiptedActionResult>() {
                @Override
                public void onResponse(Call<ReceiptedActionResult> call, Response<ReceiptedActionResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            ReceiptedActionResult result = response.body();
                            ReceiptedActionDetail receiptedActionDetail = result.getReceiptedActionDetail().get(0);
                            if (receiptedActionDetail.getErrorCode() == 0)
                                iReceiptedAction.getReceiptedAction(result);
                            else {
                                ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                                Bundle args = new Bundle();
                                args.putString("type", Constant.TYPE_NORMAL);
                                args.putString("title", context.getResources().getString(R.string.label_title_notification));
                                args.putString("firstMessageContent", receiptedActionDetail.getMessage());
                                errorMessageDialog.setArguments(args);
                                SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<ReceiptedActionResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api yêu cầu thu hộ phiếu thu
    public void receiptRequestAction(final IReceiptRequestAction iReceiptRequestAction, final ReceiptRequestActionPost receiptRequestActionPost, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            Call<ReceiptRequestActionResult> callback = httpClient.receiptRequestAction(receiptRequestActionPost);
            callback.enqueue(new Callback<ReceiptRequestActionResult>() {
                @Override
                public void onResponse(Call<ReceiptRequestActionResult> call, Response<ReceiptRequestActionResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            ReceiptRequestActionResult result = response.body();
                            ReceiptRequestActionDetail receiptRequestActionDetail = result.getReceiptRequestActionDetail().get(0);
                            if (receiptRequestActionDetail.getErrorCode() == 0)
                                iReceiptRequestAction.getReceiptRequestAction(result);
                            else {
                                ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                                Bundle args = new Bundle();
                                args.putString("type", Constant.TYPE_NORMAL);
                                args.putString("title", context.getResources().getString(R.string.label_title_notification));
                                args.putString("firstMessageContent", receiptRequestActionDetail.getMessage());
                                errorMessageDialog.setArguments(args);
                                SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<ReceiptRequestActionResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api loại đơn hàng
    public void oMOrderTypeList(final IOMOrderTypeList iomOrderTypeList, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            String url = Constant.LINK_REQUEST + "_OMOrderTypeList";
            Call<OMOrderTypeListResult> callback = httpClient.oMOrderTypeList(url);
            callback.enqueue(new Callback<OMOrderTypeListResult>() {
                @Override
                public void onResponse(Call<OMOrderTypeListResult> call, Response<OMOrderTypeListResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            OMOrderTypeListResult result = response.body();
                            iomOrderTypeList.getOMOrderTypeList(result);
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", e.getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<OMOrderTypeListResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api danh sách nhân viên
    public void oMEmployeeList(final IOMEmployeeList iOmEmployeeList, int skip, String filter, String search, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            String url = Constant.LINK_REQUEST + "_OMEmployeeList?$top=" + Constant.MAX_PAGE_ELEMENT + "&$skip=" + skip + "&$count=true&$filter="+filter+"&$&$orderby=EmployeeName&$search='" + search + "'";
            Call<OMEmployeeListResult> callback = httpClient.oMEmployeeList(url);
            callback.enqueue(new Callback<OMEmployeeListResult>() {
                @Override
                public void onResponse(Call<OMEmployeeListResult> call, Response<OMEmployeeListResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            OMEmployeeListResult result = response.body();
                            iOmEmployeeList.getOMEmployeeList(result);
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", e.getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<OMEmployeeListResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api danh sách khách hàng
    public void oMCustomerList(final IOMCustomerList iomCustomerList, String salesPersonId, int skip, String search, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            String url = Constant.LINK_REQUEST + "_OMCustomerList?$top=" + Constant.MAX_PAGE_ELEMENT + "&$skip=" + skip + "&$count=true&$filter=SalespersonId eq " + salesPersonId + "&$search='" + search + "'";
            Call<OMCustomerListResult> callback = httpClient.oMCustomerList(url);
            callback.enqueue(new Callback<OMCustomerListResult>() {
                @Override
                public void onResponse(Call<OMCustomerListResult> call, Response<OMCustomerListResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            OMCustomerListResult result = response.body();
                            iomCustomerList.getOMCustomerList(result);
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", e.getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<OMCustomerListResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api kiểm tra công nợ
    public void misaCustomerDebtCheck(final IMisaCustomerDebtCheck iMisaCustomerDebtCheck, final MisaCustomerDebtCheckPost misaCustomerDebtCheckPost, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            Call<MisaCustomerDebtCheckResult> callback = httpClient.misaCustomerDebtCheck(misaCustomerDebtCheckPost);
            callback.enqueue(new Callback<MisaCustomerDebtCheckResult>() {
                @Override
                public void onResponse(Call<MisaCustomerDebtCheckResult> call, Response<MisaCustomerDebtCheckResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            MisaCustomerDebtCheckResult result = response.body();
                            MisaCustomerDebtCheck misaCustomerDebtCheckDetail = result.getMisaCustomerDebtCheckDetail().get(0);
                            if (misaCustomerDebtCheckDetail.getErrorCode() == 0)
                                iMisaCustomerDebtCheck.getMisaCustomerDebtCheck(result);
                            else {
                                ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                                Bundle args = new Bundle();
                                args.putString("type", Constant.TYPE_NORMAL);
                                args.putString("title", context.getResources().getString(R.string.label_title_notification));
                                args.putString("firstMessageContent", misaCustomerDebtCheckDetail.getMessage());
                                errorMessageDialog.setArguments(args);
                                SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        args.putString("secondMessageContent", context.getResources().getString(R.string.title_message_not_session_second_label));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<MisaCustomerDebtCheckResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api danh sách bảng giá và sản phẩm
    public void priceListGet(final IPriceListGet iPriceListGet, final PriceListGetPost priceListGetPost, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            Call<PriceListGetResult> callback = httpClient.pricelistGet(priceListGetPost);
            callback.enqueue(new Callback<PriceListGetResult>() {
                @Override
                public void onResponse(Call<PriceListGetResult> call, Response<PriceListGetResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            PriceListGetResult result = response.body();
                            PriceListGet loginDetail = result.getPriceListGet().get(0);
                            if (loginDetail.getErrorCode() == 0)
                                iPriceListGet.getPriceListGet(result);
                            else {
                                ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                                Bundle args = new Bundle();
                                args.putString("type", Constant.TYPE_NORMAL);
                                args.putString("title", context.getResources().getString(R.string.label_title_notification));
                                args.putString("firstMessageContent", loginDetail.getMessage());
                                errorMessageDialog.setArguments(args);
                                SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        args.putString("secondMessageContent", context.getResources().getString(R.string.title_message_not_session_second_label));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<PriceListGetResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api tạo đơn hàng
    public void salesOrderAction(final ISalesOrderAction iSalesOrderAction, final SalesOrderActionPost salesOrderActionPost, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            Call<SalesOrderActionResult> callback = httpClient.salesOrderAction(salesOrderActionPost);
            callback.enqueue(new Callback<SalesOrderActionResult>() {
                @Override
                public void onResponse(Call<SalesOrderActionResult> call, Response<SalesOrderActionResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            SalesOrderActionResult result = response.body();
                            SalesOrderActionDetail salesOrderActionDetail = result.getSalesOrderActionDetail().get(0);
                            if (salesOrderActionDetail.getErrorCode() == 0)
                                iSalesOrderAction.getSalesOrderAction(result);
                            else {
                                ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                                Bundle args = new Bundle();
                                args.putString("type", Constant.TYPE_NORMAL);
                                args.putString("title", context.getResources().getString(R.string.label_title_notification));
                                args.putString("firstMessageContent", salesOrderActionDetail.getMessage());
                                errorMessageDialog.setArguments(args);
                                SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        args.putString("secondMessageContent", context.getResources().getString(R.string.title_message_not_session_second_label));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<SalesOrderActionResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api chuyển khoản ngân hàng
    public void receiptedTransferCustomerAction(final IReceiptedTransferCustomerAction iReceiptedTransferCustomerAction, final ReceiptedTransferCustomerActionPost receiptedTransferCustomerActionPost, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            Call<ReceiptedTransferCustomerActionResult> callback = httpClient.receiptedTransferCustomerAction(receiptedTransferCustomerActionPost);
            callback.enqueue(new Callback<ReceiptedTransferCustomerActionResult>() {
                @Override
                public void onResponse(Call<ReceiptedTransferCustomerActionResult> call, Response<ReceiptedTransferCustomerActionResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            ReceiptedTransferCustomerActionResult result = response.body();
                            ReceiptedTransferCustomerActionDetail receiptedTransferCustomerActionDetail = result.getReceiptedTransferCustomerActionDetail().get(0);
                            if (receiptedTransferCustomerActionDetail.getErrorCode() == 0)
                                iReceiptedTransferCustomerAction.getReceiptedTransferCustomerAction(result);
                            else {
                                ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                                Bundle args = new Bundle();
                                args.putString("type", Constant.TYPE_NORMAL);
                                args.putString("title", context.getResources().getString(R.string.label_title_notification));
                                args.putString("firstMessageContent", receiptedTransferCustomerActionDetail.getMessage());
                                errorMessageDialog.setArguments(args);
                                SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        args.putString("secondMessageContent", context.getResources().getString(R.string.title_message_not_session_second_label));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<ReceiptedTransferCustomerActionResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api danh sách ngân hàng nộp tiền
    public void arBankList(final IARBankList iarBankList,final int type, int skip, String search, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            String url = Constant.LINK_REQUEST + "_ARBankList?$top="+Constant.MAX_PAGE_ELEMENT+"&$skip="+skip+"&$count=true&$search='" + search + "'";
            Call<ARBankListResult> callback = httpClient.arBankList(url);
            callback.enqueue(new Callback<ARBankListResult>() {
                @Override
                public void onResponse(Call<ARBankListResult> call, Response<ARBankListResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            ARBankListResult result = response.body();
                            iarBankList.getARBankList(type, result);
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", e.getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<ARBankListResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api nộp tiền
    public void depositedAmountAction(final IDepositedAmountAction iDepositedAmountAction, final DepositedAmountActionPost depositedAmountActionPost, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            Call<DepositedAmountActionResult> callback = httpClient.depositedAmountAction(depositedAmountActionPost);
            callback.enqueue(new Callback<DepositedAmountActionResult>() {
                @Override
                public void onResponse(Call<DepositedAmountActionResult> call, Response<DepositedAmountActionResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            DepositedAmountActionResult result = response.body();
                            DepositedAmountActionDetail depositedAmountActionDetail = result.getDepositedAmountActionDetail().get(0);
                            if (depositedAmountActionDetail.getErrorCode() == 0)
                                iDepositedAmountAction.getDepositedAmountAction(result);
                            else {
                                ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                                Bundle args = new Bundle();
                                args.putString("type", Constant.TYPE_NORMAL);
                                args.putString("title", context.getResources().getString(R.string.label_title_notification));
                                args.putString("firstMessageContent", depositedAmountActionDetail.getMessage());
                                errorMessageDialog.setArguments(args);
                                SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        args.putString("secondMessageContent", context.getResources().getString(R.string.title_message_not_session_second_label));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<DepositedAmountActionResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api danh sách đơn hàng
    public void omSalesOrderList(final IOMSalesOrderList iomSalesOrderList, int skip, String filter,  String search, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            String url = Constant.LINK_REQUEST + "_OMSalesOrderList?$top=" + Constant.MAX_PAGE_ELEMENT + "&$skip=" + skip + "&$count=true&$filter=" +filter+ "&$search='" + search + "'";
            Call<OMSalesOrderListResult> callback = httpClient.oMSalesOrderList(url);
            callback.enqueue(new Callback<OMSalesOrderListResult>() {
                @Override
                public void onResponse(Call<OMSalesOrderListResult> call, Response<OMSalesOrderListResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            OMSalesOrderListResult result = response.body();
                            iomSalesOrderList.getOMSalesOrderList(result);
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", e.getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<OMSalesOrderListResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api chi tiết đơn hàng
    public void salesOrder(final ISalesOrder iSalesOrder, String saleOrderId, final FragmentManager fm) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            String url = Constant.LINK_REQUEST + "_SalesOrder("+saleOrderId+")?$expand=SalesOrderDetail";
            Call<SalesOrder> callback = httpClient.salesOrder(url);
            callback.enqueue(new Callback<SalesOrder>() {
                @Override
                public void onResponse(Call<SalesOrder> call, Response<SalesOrder> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            SalesOrder result = response.body();
                            iSalesOrder.getSalesOrder(result);
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", e.getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_error_access_system_dialog");
                        }
                    } else if (response.code() == 401) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NOT_SESSION);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_session));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "message_not_session_dialog");
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", errorResult.getError().getMessage());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        } catch (Exception e) {
                            ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                            Bundle args = new Bundle();
                            args.putString("type", Constant.TYPE_NORMAL);
                            args.putString("title", context.getResources().getString(R.string.label_title_notification));
                            args.putString("firstMessageContent", response.toString());
                            errorMessageDialog.setArguments(args);
                            SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<SalesOrder> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", context.getResources().getString(R.string.title_message_not_allow_connection_internet));
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    } else {
                        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
                        Bundle args = new Bundle();
                        args.putString("type", Constant.TYPE_NORMAL);
                        args.putString("title", context.getResources().getString(R.string.label_title_notification));
                        args.putString("firstMessageContent", t.getMessage());
                        errorMessageDialog.setArguments(args);
                        SystemDialog.showFragmentDialog(fm, errorMessageDialog, "title_error_connection");
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
