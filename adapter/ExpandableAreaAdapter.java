package com.scc.loomi.adapter.expandable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.scc.loomi.R;
import com.scc.loomi.api.model.Address;
import com.scc.loomi.api.model.Area;
import com.scc.loomi.api.model.ExpandItem;
import com.scc.loomi.utils.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

/**
 * Created by SAM on 12/4/2019.
 */
public class ExpandableAreaAdapter extends BaseExpandableListAdapter implements Filterable {
    private Context context;
    private ExpandCollapseListener expandCollapseListener;
    private List<ExpandItem> expandItemList = new ArrayList<>();
    private List<ExpandItem> mFilteredExpandItemList = new ArrayList<>();
    private boolean isEnglish, needExpand = false;

    public ExpandableAreaAdapter(Context context, List<ExpandItem> expandItemList, ExpandCollapseListener expandCollapseListener) {
        this.context = context;
        this.expandItemList = expandItemList;
        mFilteredExpandItemList = expandItemList;
        this.expandCollapseListener = expandCollapseListener;
        isEnglish = SharedPrefs.isEnglish(context);
    }

    public List<ExpandItem> getFilterList() {
        return mFilteredExpandItemList;
    }

    @Override
    public int getGroupCount() {
        return this.mFilteredExpandItemList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ExpandItem expandItem = mFilteredExpandItemList.get(groupPosition);
//        return expandItem.isCountry() ? expandItem.getCityList().size() : expandItem.getAddressList().size();
        return expandItem.isCountry() ? expandItem.getAreas().size() : expandItem.getAddressList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        ExpandItem expandItem = mFilteredExpandItemList.get(groupPosition);
        return expandItem.isCountry() ? isEnglish ? expandItem.getCountry().getNameEn() : expandItem.getCountry().getNameAr() : expandItem.getTitle();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ExpandItem expandItem = mFilteredExpandItemList.get(groupPosition);
        return expandItem.isCountry() ? expandItem.getAreas().get(childPosition) : expandItem.getAddressList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_item_section_header, null);
        }

        TextView listTitleTextView = convertView.findViewById(R.id.tv_title);
        ImageView imageView = convertView.findViewById(R.id.iv_arrow);

        imageView.setImageDrawable(context.getResources().getDrawable(isExpanded ? R.drawable.ic_arrow_drop_up_color_primary_dark : R.drawable.ic_arrow_drop_down_color_primary_dark));
        listTitleTextView.setTypeface(null, Typeface.BOLD);

        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Object object = getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_item_section_item, null);
        }
        TextView expandedListTextView = convertView.findViewById(R.id.tv_cities);
        ImageView ivSelect = convertView.findViewById(R.id.iv_select);

        if (object instanceof Address.Datum) {
            ivSelect.setVisibility(GONE);
            Address.Datum address = (Address.Datum) object;
            expandedListTextView.setText(address.getHouseNo() + ", " + address.getAddressName() + ", " + address.getDescription()
                    + " - " + (SharedPrefs.isEnglish(context) ? address.getArea().getNameEn() :
                    address.getArea().getNameAr().isEmpty() ? address.getArea().getNameEn() : address.getArea().getNameAr()));
        } else {

            Area city = (Area) object;
            ivSelect.setVisibility(city.isSelected() ? View.VISIBLE : GONE);
            expandedListTextView.setText(isEnglish ? city.getNameEn() : city.getNameAr());
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                String charString = constraint.toString().toLowerCase();

                if (charString.isEmpty()) {
                    mFilteredExpandItemList = expandItemList;
                    needExpand = false;
//                    expandCollapseListener.listen(false);
                } else {
                    ArrayList<ExpandItem> filteredList = new ArrayList<>();

                    for (ExpandItem item : expandItemList) {
                        if (item.isCountry()) {
                            List<Area> newCityList = new ArrayList<>();
                            for (Area area : item.getAreas()) {
                                if (SharedPrefs.isEnglish(context) ? area.getNameEn().toLowerCase().contains(charString) :
                                        area.getNameAr().toLowerCase().contains(charString)) {
                                    newCityList.add(area);
                                }
                            }
                            if (newCityList.size() > 0) {
                                filteredList.add(new ExpandItem(item.getCountry(), newCityList));
                            }
                        } else {
                            List<Address.Datum> newAddressList = new ArrayList<>();
                            for (Address.Datum address : item.getAddressList()) {
                                if (address.getAddressAreas().toLowerCase().contains(charString)) {
                                    newAddressList.add(address);
                                }
                            }
                            if (newAddressList.size() > 0) {
                                filteredList.add(new ExpandItem(context.getString(R.string.saved_address), newAddressList));
                            }

                        }
//                        if (item.isCountry()) {
//                            List<City> newCityList = new ArrayList<>();
//                            for (City city : item.getCityList()) {
//                                if (SharedPrefs.isEnglish(context) ? city.getName().toLowerCase().contains(charString) :
//                                        city.getName_ar().toLowerCase().contains(charString)) {
//                                    newCityList.add(city);
//                                }
//                            }
//                            if (newCityList.size() > 0) {
//                                filteredList.add(new ExpandItem(item.getCountry(), newCityList));
//                            }
//                        }
//                        else {
//                            List<Address> newAddressList = new ArrayList<>();
//                            for (Address address : item.getAddressList()) {
//                                if (address.getAddressAreas().toLowerCase().contains(charString)) {
//                                    newAddressList.add(address);
//                                }
//                            }
//                            if (newAddressList.size() > 0) {
//                                filteredList.add(new ExpandItem(context.getString(R.string.saved_address), newAddressList));
//                            }
//                        }
                    }
                    needExpand = true;
//                    expandCollapseListener.listen(true);
                    mFilteredExpandItemList = filteredList;
                }
                filterResults.count = mFilteredExpandItemList.size();
                filterResults.values = mFilteredExpandItemList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null) {
                    notifyDataSetChanged();
                    expandCollapseListener.listen(needExpand);
                }
            }
        };
    }

    public interface ExpandCollapseListener {
        void listen(boolean w);
    }
}
