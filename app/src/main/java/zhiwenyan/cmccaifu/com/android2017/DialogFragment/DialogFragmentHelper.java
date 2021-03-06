package zhiwenyan.cmccaifu.com.android2017.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：4/8/2018-6:46 PM
 *
 * @author: yanzhiwen
 */
public class DialogFragmentHelper {

    private static final String DIALOG_POSITIVE = "确定";
    private static final String DIALOG_NEGATIVE = "取消";

    private static final String TAG_HEAD = DialogFragmentHelper.class.getSimpleName();

    /**
     * 加载中的弹出窗
     */
    private static final int PROGRESS_THEME = R.style.Base_AlertDialog;
    private static final String PROGRESS_TAG = TAG_HEAD + ":progress";

    public static CommonDialogFragment showProgress(FragmentManager fragmentManager, String message) {
        return showProgress(fragmentManager, message, true, null);
    }

    public static CommonDialogFragment showProgress(FragmentManager fragmentManager, String message, boolean cancelable) {
        return showProgress(fragmentManager, message, cancelable, null);
    }

    public static CommonDialogFragment showProgress(FragmentManager fragmentManager, final String message, boolean cancelable
            , CommonDialogFragment.OnDialogCancelListener cancelListener) {

        CommonDialogFragment dialogFragment = CommonDialogFragment.newInstance(new CommonDialogFragment.OnCallDialog() {
            @Override
            public Dialog getDialog(Context context) {
                ProgressDialog progressDialog = new ProgressDialog(context, PROGRESS_THEME);
                progressDialog.setMessage(message);
                return progressDialog;
            }
        }, cancelable, cancelListener);
        dialogFragment.show(fragmentManager, PROGRESS_TAG);
        return dialogFragment;
    }

    /**
     * 简单提示弹出窗
     */
    private static final int TIPS_THEME = R.style.Base_AlertDialog;
    private static final String TIPS_TAG = TAG_HEAD + ":tips";

    public static void showTips(FragmentManager fragmentManager, String message) {
        showTips(fragmentManager, message, true, null);
    }

    public static void showTips(FragmentManager fragmentManager, String message, boolean cancelable) {
        showTips(fragmentManager, message, cancelable, null);
    }

    public static void showTips(FragmentManager fragmentManager, final String message, boolean cancelable
            , CommonDialogFragment.OnDialogCancelListener cancelListener) {

        CommonDialogFragment dialogFragment = CommonDialogFragment.newInstance(new CommonDialogFragment.OnCallDialog() {
            @Override
            public Dialog getDialog(Context context) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, TIPS_THEME);
                builder.setMessage(message);
                return builder.create();
            }
        }, cancelable, cancelListener);
        dialogFragment.show(fragmentManager, TIPS_TAG);
    }


    /**
     * 确定取消框
     */
    private static final int CONFIRM_THEME = R.style.Base_AlertDialog;
    private static final String CONfIRM_TAG = TAG_HEAD + ":confirm";

    public static void showConfirmDialog(FragmentManager fragmentManager, final String message, DialogInterface.OnClickListener onOkListener,
                                         DialogInterface.OnClickListener onCancelListener, boolean cancelable, CommonDialogFragment.OnDialogCancelListener dialogCancelListener) {
        CommonDialogFragment dialogFragment = CommonDialogFragment.newInstance(context -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, CONFIRM_THEME)
                    .setMessage(message)
                    .setPositiveButton(DIALOG_POSITIVE, onOkListener)
                    .setNegativeButton(DIALOG_NEGATIVE, onCancelListener);
            return builder.create();
        }, cancelable, dialogCancelListener);
        dialogFragment.show(fragmentManager, CONfIRM_TAG);
    }


    /**
     * 带列表的弹出窗
     */
    private static final int LIST_THEME = R.style.Base_AlertDialog;
    private static final String LIST_TAG = TAG_HEAD + ":list";

    public static void showListDialog(FragmentManager fragmentManager, final String title, final String[] items
            , final IDialogResultListener<Integer> resultListener, boolean cancelable) {
        CommonDialogFragment dialogFragment = CommonDialogFragment.newInstance(context -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, LIST_THEME)
                    .setTitle(title)
                    .setItems(items, (dialog, which) -> {
                        if (resultListener != null) {
                            resultListener.onDataResult(which);
                        }
                    });
            return builder.create();
        }, cancelable, null);
        dialogFragment.show(fragmentManager, LIST_TAG);
    }

    /**
     * 选择日期
     */
    private static final int DATE_THEME = R.style.Base_AlertDialog;
    private static final String DATE_TAG = TAG_HEAD + ":date";

    public static void showDateDialog(FragmentManager fragmentManager, final String title, final Calendar calendar
            , final IDialogResultListener<Calendar> resultListener, final boolean cancelable) {
        CommonDialogFragment dialogFragment = CommonDialogFragment.newInstance(context -> {
            final DatePickerDialog datePickerDialog = new DatePickerDialog(context, DATE_THEME, (view, year, month, dayOfMonth) -> {
                calendar.set(year, month, dayOfMonth);
                resultListener.onDataResult(calendar);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

            datePickerDialog.setTitle(title);
            datePickerDialog.setOnShowListener(dialog -> {
                datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setText(DIALOG_POSITIVE);
                datePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setText(DIALOG_NEGATIVE);
            });
            return datePickerDialog;

        }, cancelable, null);
        dialogFragment.show(fragmentManager, DATE_TAG);
    }


    /**
     * 选择时间
     */
    private static final int TIME_THEME = R.style.Base_AlertDialog;
    private static final String TIME_TAG = TAG_HEAD + ":time";

    public static void showTimeDialog(FragmentManager manager, final String title, final Calendar calendar, final IDialogResultListener<Calendar> resultListener, final boolean cancelable) {
        CommonDialogFragment dialogFragment = CommonDialogFragment.newInstance(context -> {
            final TimePickerDialog dateDialog = new TimePickerDialog(context, TIME_THEME, (view, hourOfDay, minute) -> {
                if (resultListener != null) {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);
                    resultListener.onDataResult(calendar);
                }
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

            dateDialog.setTitle(title);
            dateDialog.setOnShowListener(dialog -> {
                dateDialog.getButton(DialogInterface.BUTTON_POSITIVE).setText(DIALOG_POSITIVE);
                dateDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setText(DIALOG_NEGATIVE);
            });

            return dateDialog;
        }, cancelable, null);
        dialogFragment.show(manager, DATE_TAG);
    }

    /**
     * 带输入框的弹出窗
     */
    private static final int INSERT_THEME = R.style.Base_AlertDialog;
    private static final String INSERT_TAG = TAG_HEAD + ":insert";

    public static void showInsertDialog(FragmentManager manager, final String title, final IDialogResultListener<String> resultListener, final boolean cancelable) {

        CommonDialogFragment dialogFragment = CommonDialogFragment.newInstance(context -> {
            final EditText editText = new EditText(context);
            editText.setBackground(null);
            editText.setPadding(60, 40, 0, 0);
            AlertDialog.Builder builder = new AlertDialog.Builder(context, INSERT_THEME);
            builder.setTitle(title);
            builder.setView(editText);
            builder.setPositiveButton(DIALOG_POSITIVE, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (resultListener != null) {
                        resultListener.onDataResult(editText.getText().toString());
                    }
                }
            });
            builder.setNegativeButton(DIALOG_NEGATIVE, null);
            return builder.create();

        }, cancelable, null);
        dialogFragment.show(manager, INSERT_TAG);

    }


    /**
     * 带输入密码框的弹出窗
     */
    private static final int PASSWORD_INSER_THEME = R.style.Base_AlertDialog;
    private static final String PASSWORD_INSERT_TAG = TAG_HEAD + ":insert";

    public static void showPasswordInsertDialog(FragmentManager manager, final String title, final IDialogResultListener<String> resultListener, final boolean cancelable) {
        CommonDialogFragment dialogFragment = CommonDialogFragment.newInstance(new CommonDialogFragment.OnCallDialog() {
            @Override
            public Dialog getDialog(Context context) {
                final EditText editText = new EditText(context);
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                editText.setEnabled(true);
                AlertDialog.Builder builder = new AlertDialog.Builder(context, PASSWORD_INSER_THEME);
                builder.setTitle(title);
                builder.setView(editText);
                builder.setPositiveButton(DIALOG_POSITIVE, (dialog, which) -> {
                    if (resultListener != null) {
                        resultListener.onDataResult(editText.getText().toString());
                    }
                });
                builder.setNegativeButton(DIALOG_NEGATIVE, null);
                return builder.create();
            }
        }, cancelable, null);
        dialogFragment.show(manager, INSERT_TAG);
    }

    /**
     * 两个输入框的弹出窗
     */
    private static final int INTERVAL_INSERT_THEME = R.style.Base_AlertDialog;
    private static final String INTERVAL_INSERT_TAG = TAG_HEAD + ":interval_insert";

    public static void showIntervalInsertDialog(FragmentManager manager, final String title, final IDialogResultListener<String[]> resultListener, final boolean cancelable) {
        CommonDialogFragment dialogFragment = CommonDialogFragment.newInstance(context -> {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_interval_insert, null);
            final EditText minEditText = ( EditText ) view.findViewById(R.id.interval_insert_et_min);
            final EditText maxEditText = ( EditText ) view.findViewById(R.id.interval_insert_et_max);
            AlertDialog.Builder builder = new AlertDialog.Builder(context, INTERVAL_INSERT_THEME);
            return builder.setTitle(title)
                    .setView(view)
                    .setPositiveButton(DIALOG_POSITIVE, (dialog, which) -> {
                        if (resultListener != null) {
                            resultListener.onDataResult(new String[]{minEditText.getText().toString(), maxEditText.getText().toString()});
                        }
                    }).setNegativeButton(DIALOG_NEGATIVE, null)
                    .create();
        }, cancelable, null);
        dialogFragment.show(manager, INTERVAL_INSERT_TAG);
    }


}