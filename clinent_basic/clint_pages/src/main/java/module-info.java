module orgs.clint_pages {
    requires javafx.controls;
    requires javafx.fxml;
<<<<<<< HEAD
    requires javafx.swt;
    requires java.desktop;
=======
    requires java.sql;
>>>>>>> a2252d50341e779b927e20ae3bfc92f35ffc396c


    opens orgs.clint_pages to javafx.fxml;
    exports orgs.clint_pages;
    exports orgs.clint_pages.controllers;
    opens orgs.clint_pages.controllers to javafx.fxml;
}