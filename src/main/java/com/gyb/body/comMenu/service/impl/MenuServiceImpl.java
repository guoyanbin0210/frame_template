package com.gyb.body.comMenu.service.impl;
import com.gyb.body.comMenu.dao.MenuDao;
import com.gyb.body.apiMoudel.ApiMenuModel;
import com.gyb.body.comMenu.model.MenuModel;
import com.gyb.body.comMenu.service.MenuService;
import com.gyb.base.dao.BaseDao;
import com.gyb.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuModel> implements MenuService{
    @Resource 
    private MenuDao dao;
    @Override
    public BaseDao<MenuModel> getBaseDao() {
        return dao;
    }

    @Override
    public List<ApiMenuModel> findDataByMenu() {
        //将层级依次查出来
        List<MenuModel> list1 = dao.selectMenu("0");
        List<MenuModel> list2 = dao.selectMenu("1");
        List<MenuModel> list3 = dao.selectMenu("2");
        List<ApiMenuModel> newData = new ArrayList<>();
        for (MenuModel model : list1){
            ApiMenuModel apiData = new ApiMenuModel();
            apiData.setId(model.getId());
            apiData.setName(model.getMenu_name());
            apiData.setSort(model.getMenu_sort());
            apiData.setLevel(model.getMenu_level());
            apiData.setMenu_url(model.getMenu_url());
            List<ApiMenuModel> data = new ArrayList<>();
            for (MenuModel model2:list2){
                //检查子集与父级对应关系
                if (model2.getMenu_parent_id().equals(model.getId())){
                    ApiMenuModel apiMenuModel = new ApiMenuModel();
                    apiMenuModel.setId(model2.getId());
                    apiMenuModel.setName(model2.getMenu_name());
                    apiMenuModel.setSort(model2.getMenu_sort());
                    apiMenuModel.setLevel(model2.getMenu_level());
                    apiMenuModel.setMenu_url(model2.getMenu_url());
                    data.add(apiMenuModel);
                    apiData.setModels(data);
                    //检查子集与孙子集对应关系
                    List<ApiMenuModel> data2 = new ArrayList<>();
                    for (MenuModel model3:list3){
                        if (model3.getMenu_parent_id().equals(model2.getId())){
                            ApiMenuModel apiMenu = new ApiMenuModel();
                            apiMenu.setId(model3.getId());
                            apiMenu.setName(model3.getMenu_name());
                            apiMenu.setSort(model3.getMenu_sort());
                            apiMenu.setLevel(model3.getMenu_level());
                            apiMenu.setMenu_url(model3.getMenu_url());
                            data2.add(apiMenu);
                            apiMenuModel.setModels(data2);
                        }
                    }
                }
            }
            newData.add(apiData);
        }
        return newData;
    }

    @Override
    public List<MenuModel> findDataByType(String type) {

        return dao.selectMenu(type);
    }

    @Override
    public List<MenuModel> findDataById(String id,String type) {

        return dao.selectMenuById(id,type);
    }

    @Override
    public List<ApiMenuModel> findDataByParentId(String parent_id) {
        List<MenuModel> model = dao.selectMenuByParentId(parent_id);
        List<MenuModel> childModel = dao.selectMenu("2");
        List<ApiMenuModel> newData = new ArrayList<>();
        for (MenuModel menuModel : model){
            ApiMenuModel apiData = new ApiMenuModel();
            apiData.setId(menuModel.getId());
            apiData.setName(menuModel.getMenu_name());
            apiData.setSort(menuModel.getMenu_sort());
            apiData.setLevel(menuModel.getMenu_level());
            List<ApiMenuModel> data = new ArrayList<>();
            for (MenuModel menuModel2 : childModel){
                if (menuModel2.getMenu_parent_id().equals(menuModel.getId())) {
                    ApiMenuModel apiMenuModel = new ApiMenuModel();
                    apiMenuModel.setId(menuModel2.getId());
                    apiMenuModel.setName(menuModel2.getMenu_name());
                    apiMenuModel.setSort(menuModel2.getMenu_sort());
                    apiMenuModel.setLevel(menuModel2.getMenu_level());
                    data.add(apiMenuModel);
                    apiData.setModels(data);
                }
            }
            newData.add(apiData);
        }
        return newData;
    }

}
