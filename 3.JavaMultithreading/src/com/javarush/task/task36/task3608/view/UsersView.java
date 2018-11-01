package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

/**
 * Created by Basillio on 24.03.2017.
 */
public class UsersView implements View {

    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {
        String userType = "";
        if(modelData.isDisplayDeletedUserList()) { userType = "All deleted users:";
        } else
        { userType = "All users:";}
        System.out.println(userType);
        for (User user: modelData.getUsers()) {
            System.out.println("\t" + user.toString());
        }
        System.out.println("===================================================");

    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;

    }

    public void fireEventShowAllUsers() {
        controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }

    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }
}
