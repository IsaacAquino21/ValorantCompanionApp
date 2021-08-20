package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.interfaces;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.DynamicRVModel;

public interface UpdateRecyclerView {
    public void callback(int position, ArrayList<DynamicRVModel> items);
}
