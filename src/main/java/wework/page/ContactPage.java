package wework.page;

import org.openqa.selenium.By;

public class ContactPage extends BasePage {
    public ContactPage addMember(String name, String englishName, String acctid, String mobile) {
        clickBy(By.linkText("添加成员"));
        sendKeys(By.id("username"), name);
        sendKeys(By.id("memberAdd_english_name"), englishName);
        sendKeys(By.id("memberAdd_acctid"), acctid);
        sendKeys(By.id("memberAdd_phone"), mobile);
        clickBy(By.cssSelector(".member_colRight_operationBar:nth-child(3) > .js_btn_save"));
        return this;
    }

    public ContactPage search(String wd) {
        sendKeys(By.id("memberSearchInput"), wd);
        return this;
    }

    public ContactPage clearSearch() {
        clearKeys(By.id("memberSearchInput"));
        return this;
    }

    public ContactPage deleteMember() {
        clickBy(By.cssSelector(".js_del_member"));
        clickBy(By.cssSelector(".qui_dialog_foot > .ww_btn_Blue"));
        return this;
    }

    public ContactPage addDepartment(String department) {
        clickBy(By.cssSelector(".member_colLeft_top_addBtn"));
        clickBy(By.cssSelector(".js_create_party"));
        sendKeys(By.cssSelector(".inputDlg_item:nth-child(1) > .qui_inputText"), department);
        clickBy(By.cssSelector(".js_toggle_party_list"));
        clickBy(By.xpath("(//a[@id='1688850826773722_anchor'])[2]"));
        clickBy(By.cssSelector(".qui_dialog_foot > .ww_btn_Blue"));    // 确定
        return this;
    }

    public ContactPage deleteDepartment(String department) {
        clickBy(By.linkText(department));
        clickBy(By.xpath("//li[2]/a/span"));
        clickBy(By.xpath("(//a[contains(text(),'删除')])[3]"));
        clickBy(By.linkText("确定"));
        return this;
    }

    /**
     *
     * @param tagName: 标签名字
     * @param tagUser: 使用人
     * @return
     */
    public ContactPage addTag(String tagName, String tagUser) {
        clickBy(By.xpath("//a[contains(text(),'标签')]"));
        clickBy(By.cssSelector(".member_colLeft_top_addBtn"));
        sendKeys(By.cssSelector(".qui_inputText:nth-child(2)"), tagName);
        clickBy(By.cssSelector(".js_toggle_share_range"));
        clickBy(By.xpath(String.format("//a[contains(text(),'%s')]", tagUser)));
        clickBy(By.linkText("确定"));
        return this;
    }

    public ContactPage deleteTag(String tagName) {
        clickBy(By.xpath("//a[contains(text(),'标签')]"));
        clickBy(By.xpath(String.format("//li[contains(text(), '%s')]", tagName)));
        clickBy(By.xpath("//a[@class='member_tag_list_moreBtn']"));
        clickBy(By.xpath("//a[contains(text(),'删除')]"));
        clickBy(By.linkText("确定"));
        return this;
    }
}
