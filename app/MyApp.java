public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @NotNull
                @Override
                protected String createStackElementTag(@NotNull StackTraceElement element) {
                    return String.format("%s->%s", super.createStackElementTag(element), element.getLineNumber());
                }
            });
        }

    }
}
