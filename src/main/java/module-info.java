open module org.panda.systems.kakeipon.main {
    requires jakarta.persistence;
    requires jakarta.validation;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.tx;
    requires spring.web;
    exports org.panda.systems.kakeipon;
}