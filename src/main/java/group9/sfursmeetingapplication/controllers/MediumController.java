package group9.sfursmeetingapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import group9.sfursmeetingapplication.models.Medium;
import group9.sfursmeetingapplication.repositories.MediumRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.ParseException;
import java.util.Map;

@Controller
public class MediumController {
     @Autowired
    private MediumRepository mediumRepo;

    @PostMapping("/create-medium")
    public String createMedium(@RequestParam Map<String, String> mediumData) throws ParseException {
    String name = mediumData.get("name");
    int remote = Integer.parseInt(mediumData.get("remote")); 

    Medium newMedium = new Medium();
    newMedium.setName(name);
    
    if(remote == 0) {
        newMedium.setRemote(false);;
        mediumRepo.save(newMedium);
    }

    else {
        newMedium.setRemote(true);
        mediumRepo.save(newMedium);
    }

    return "redirect:/dashboard"; 
} 

}

