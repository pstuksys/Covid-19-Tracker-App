package com.example.Covid19.controllers;

import com.example.Covid19.modules.LocationStats;
import com.example.Covid19.services.CorovaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CorovaVirusDataService corovaVirusDataService;

    @GetMapping("/")
    public String Home(Model model){
        List<LocationStats> allStats = corovaVirusDataService.getAllStats();
       int totalReportedCases =  allStats.stream().mapToInt(stat ->stat.getLatestTotalCases()).sum();
       int  totalNewCases = allStats.stream().mapToInt(stat ->stat.getDiffFromPrevDay()).sum();

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
