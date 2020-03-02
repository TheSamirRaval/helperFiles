

/**
 * Created by SAM on 11/6/2019.
 */
public class AutoSearchLabelSizeAdapter extends ArrayAdapter<LabelSize.Datum> implements Filterable {

    private LayoutInflater mInflater;
    private int mResource;
    private OnItemSelectListener<LabelSize.Datum> onItemSelectListener;
    private Context mContext;
    private String AUTH_TOKEN;
    private List<LabelSize.Datum> mList = new ArrayList<>();
    private String searchKey = "";
    private boolean needToCall = true;
    private CompositeDisposable disposable;

    public AutoSearchLabelSizeAdapter(@NonNull Context context, int resource, OnItemSelectListener<LabelSize.Datum> onItemSelectListener,
                                      CompositeDisposable disposable) {
        super(context, resource);
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mResource = resource;
        this.onItemSelectListener = onItemSelectListener;
        this.AUTH_TOKEN = SharedPrefs.getString(context, SharedPrefs.TOKEN);
        this.disposable = disposable;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(mResource, parent, false);
        }
        convertView.setOnClickListener(v -> {
            needToCall = false;
            new Handler().postDelayed(() -> needToCall = true, 100);
            onItemSelectListener.onItemSelect(position, mList.get(position));
        });

        TextView textView = convertView.findViewById(android.R.id.text1);

        LabelSize.Datum data = mList.get(position);

        setTextWithSpan(textView, data.getSize(), data.getCut(), data.getUps());

        return convertView;

//        if (position < mList.size() - 1) {
//        }
//        else return super.getView(position, convertView, parent);
    }

    private void setTextWithSpan(TextView textView, String size, String cut, String ups) {
        if (size == null) return;

        String fCut = cut == null ? "" : " " + cut.trim() + "-cut";
        String fUps = ups == null ? "" : " " + ups.trim() + "-ups";

        String finalText = size + fCut + fUps;

        SpannableStringBuilder sb = new SpannableStringBuilder(finalText);

        try {
            if (!searchKey.isEmpty()) {
                int start = finalText.toLowerCase().indexOf(searchKey.toLowerCase());
                int end = start + searchKey.length();
                sb.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                sb.setSpan(new ForegroundColorSpan(Color.BLACK), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!fCut.equals("")) {
            int start = finalText.indexOf(fCut);
            int end = start + fCut.length();
//            sb.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            sb.setSpan(new ForegroundColorSpan(Color.GREEN), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (!fUps.equals("")) {
            int start = finalText.indexOf(fUps);
            int end = start + fUps.length();
//            sb.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            sb.setSpan(new ForegroundColorSpan(Color.BLUE), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        textView.setText(sb);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public LabelSize.Datum getItem(int position) {
        return mList.get(position);
    }

    @SuppressLint("CheckResult")
    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (TextUtils.isEmpty(constraint)) return filterResults;
                if (searchKey.equals(constraint.toString())) return filterResults;
                if (!needToCall) return filterResults;

                searchKey = constraint.toString();


                disposable.add(ApiClient.getApiService()
                        .getLabelSizeOb(ACTION_GET_LABEL_SIZE, AUTH_TOKEN, searchKey)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(labelSize -> {
                            if (labelSize.getStatus()) {
                                if (labelSize.getData() != null) {
                                    mList = labelSize.getData();

                                    filterResults.values = mList;
                                    filterResults.count = mList.size();

                                    if (filterResults.count > 0) notifyDataSetChanged();
                                    else notifyDataSetInvalidated();
                                }
                            }
                            Timber.d("resetLabelSizeData: %s", labelSize.getData().toString());
                        }, Throwable::printStackTrace));


                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }

}
