/**
 * Created by SAM on 9/16/2019.
 */
public class PermissionDialogUtils {

    public static void showRationalDialog(@NonNull Context context, @StringRes int messageId, final PermissionRequest request) {
        new AlertDialog.Builder(context)
                .setMessage(messageId)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> request.proceed())
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> request.cancel())
                .show();
    }

    public static void showDeniedDialog(@NonNull Context context, String permissionName) {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setMessage(String.format(context.getResources().getString(R.string.permission_denied), permissionName, permissionName))
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    public static void showNeverAskDialog(@NonNull final Context context, String permissionName) {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setMessage(String.format(context.getResources().getString(R.string.permission_never_ask_again), permissionName))
                .setPositiveButton(R.string.goto_setting, (dialog, which) -> PermissionDialogUtils.openSettings(context))
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    public static void showNeverAskDialogForLocation(@NonNull final Context context) {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setMessage("Go to 'settings' and give permission to track location.")
                .setPositiveButton(R.string.goto_setting, (dialog, which) -> PermissionDialogUtils.openSettings(context))
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    private static void openSettings(@NonNull Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
