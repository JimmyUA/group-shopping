package com.sergey.prykhodko.front.pages.admin.admincabinet.client;

import com.sergey.prykhodko.front.pages.basepage.adminbasepage.AdminBasePage;

public class ClientForAdminPage extends AdminBasePage {

    private Integer ownerId;

    public ClientForAdminPage(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
