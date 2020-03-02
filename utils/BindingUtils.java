public class BindingUtils {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        GlideApp.with(view.getContext()).load(url).centerCrop().into(view);
    }


    @BindingAdapter("bind:circleImage")
    public static void loadCircleImage(ImageView view, String url) {
        GlideApp.with(view.getContext()).load(url).circleCrop().into(view);
    }


    public static String getDate(String date) {
        SimpleDateFormat oldSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM", Locale.getDefault());
        try {
            return sdf.format(Objects.requireNonNull(oldSdf.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

    }
}
