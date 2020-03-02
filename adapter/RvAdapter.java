

/**
 * Created by SAM on 12/9/2019.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Search.Datum> mList = new ArrayList<>();
    private OnItemSelectListener<Search.Datum> onItemSelectListener;

    public SearchAdapter(OnItemSelectListener<Search.Datum> onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RowItemSearchCheckPointBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.row_item_search_check_point, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<Search.Datum> list) {
        this.mList.clear();
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(int startIndex, List<Search.Datum> list) {
        if (startIndex == 1) {
            this.mList.clear();
            this.mList.addAll(list);
            notifyDataSetChanged();
        } else {
            int index = mList.size();
            this.mList.addAll(list);
            notifyItemRangeInserted(index, list.size());
        }
    }

    public List<Search.Datum> getData() {
        return this.mList;
    }

    public void clearData() {
        this.mList.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RowItemSearchCheckPointBinding binding;

        private ViewHolder(RowItemSearchCheckPointBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;

            itemView.setOnClickListener(v -> onItemSelectListener.onItemSelect(getAdapterPosition(), mList.get(getAdapterPosition())));
        }

        private void bindData(Search.Datum model) {
            binding.setSearch(model);
            binding.executePendingBindings();


        }
    }
}