/**
 * Created by SAM on 9/16/2019.
 */
public class DialogUtil {

    private static final String TAG_FRAGMENT = "Dialog";

    private static DialogFragment mDialogFragment;

    public static void showProgressDialog(Activity activity, FragmentManager fragmentManager) {
        if (activity == null || activity.isFinishing()) {
            return;
        }

        if (mDialogFragment != null && mDialogFragment.isVisible()) {
            return;
        }

        DialogFragment mPrevious = mDialogFragment;
        mDialogFragment = new ProgressDialogFragment();

        mDialogFragment.setCancelable(false);

        if (mPrevious != null) {
            fragmentManager.beginTransaction().remove(mPrevious).commitAllowingStateLoss();
        }
        fragmentManager.beginTransaction().add(mDialogFragment, TAG_FRAGMENT).commitAllowingStateLoss();
    }

    public static void dismissProgressDialog() {
        if (mDialogFragment != null) {
            mDialogFragment.dismissAllowingStateLoss();
            mDialogFragment = null;
        }
    }

    public static void alertDialog(@NonNull Context context, String msg) {
        new AlertDialog.Builder(context)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    public static void alertDialog(@NonNull Context context, String title, String msg) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    public static void onErrorDialog(@NonNull Context context, String message) {
        new AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    public static void onFailureDialog(@NonNull Context context, String message) {
        new AlertDialog.Builder(context)
                .setTitle("Oops...")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}