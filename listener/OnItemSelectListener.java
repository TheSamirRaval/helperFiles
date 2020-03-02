/**
 * Created by SAM on 10/11/2019.
 */
public interface OnItemSelectListener<T> {
    void onItemSelect(int position, T t);

    void onItemLongClick(int position, T t);
}