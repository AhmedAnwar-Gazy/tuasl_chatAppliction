module orgs.clint_pages {
    requires javafx.controls;
    requires javafx.fxml;


    opens orgs.clint_pages to javafx.fxml;
    exports orgs.clint_pages;
    exports orgs.clint_pages.controllers;
    opens orgs.clint_pages.controllers to javafx.fxml;
}