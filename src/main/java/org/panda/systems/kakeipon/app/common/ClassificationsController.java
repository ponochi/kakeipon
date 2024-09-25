package org.panda.systems.kakeipon.app.common;

import org.panda.systems.kakeipon.domain.model.common.FirstClassification;
import org.panda.systems.kakeipon.domain.model.common.SecondClassification;
import org.panda.systems.kakeipon.domain.model.common.ThirdClassification;
import org.panda.systems.kakeipon.domain.service.common.FirstClassificationService;
import org.panda.systems.kakeipon.domain.service.common.SecondClassificationService;
import org.panda.systems.kakeipon.domain.service.common.ThirdClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    return form;
  }

  @GetMapping( "" )
  String list(Model model) {
    List<FirstClassification> firstClasses
        = firstClassificationService.findAll();
    List<SecondClassification> secondClasses
        = secondClassificationService.findAll();
    List<ThirdClassification> thirdClasses
        = thirdClassificationService.findAll();

    model.addAttribute("firstClasses", firstClasses);
    model.addAttribute("secondClasses", secondClasses);
    model.addAttribute("thirdClasses", thirdClasses);
    return "/common/showClasses";
  }
}
