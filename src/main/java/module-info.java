module org.panda.systems.kakeipon {
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.context;
  requires spring.beans;
  requires spring.tx;
  requires spring.data.jpa;
  requires jakarta.persistence;
  requires jakarta.validation;
  requires spring.web;
  requires spring.webmvc;
  requires spring.boot.starter.data.jpa;
  requires spring.boot.starter.web;
  requires spring.boot.starter.validation;
  exports org.panda.systems.kakeipon;
  exports org.panda.systems.kakeipon.app.user;
  exports org.panda.systems.kakeipon.domain.model.user;
  exports org.panda.systems.kakeipon.domain.repository.user;
  exports org.panda.systems.kakeipon.domain.service.user;
  opens org.panda.systems.kakeipon;
  opens org.panda.systems.kakeipon.app.user;
  opens org.panda.systems.kakeipon.domain.model.user;
  opens org.panda.systems.kakeipon.domain.repository.user;
  opens org.panda.systems.kakeipon.domain.service.user;
}
