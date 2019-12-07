package com.github.tenx.xcom.ui.Function;

import com.github.tenx.xcom.base.BaseViewModel;
import com.github.tenx.xcom.data.AppDataManager;

public class FunctionViewModel extends BaseViewModel implements FunctionViewModelHelper {
    
    private AppDataManager appDataManager;

    private static final String TAG = "FunctionViewModel";
    
    public FunctionViewModel(AppDataManager dataManager) {
        super(dataManager);
        this.appDataManager = dataManager;
    }
    
    
    
}
