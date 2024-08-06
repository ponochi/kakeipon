package org.panda.systems.kakeipon.app.common;

import org.panda.systems.kakeipon.domain.service.common.FirstClassificationService;
import org.panda.systems.kakeipon.domain.service.common.SecondClassificationService;
import org.panda.systems.kakeipon.domain.service.common.ThirdClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "classification" )
public class ClassificationsController {
  @Autowired
  FirstClassificationService firstClassificationService;
  @Autowired
  SecondClassificationService secondClassificationService;
  @Autowired
  ThirdClassificationService thirdClassificationService;

  @ModelAttribute
  ClassificationsForm setUpForm( )
  {
    ClassificationsForm form = new ClassificationsForm( );
    if (form.getClasses() == null ) {
//      form.setRole(roleService.findByRoleId( form.getRoleId() ) );
    }
    return form;
  }

  @GetMapping( "" )
  String list( ) {
    return "/common/showList";
  }
}
