package com.cloudbees.breizhcamp.controllers;

import com.cloudbees.breizhcamp.services.Schedule;
import com.cloudbees.breizhcamp.domain.Talk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Guernion Sylvain
 */
@Controller
public class TalkController {

    @Autowired
    private Schedule schedule;

    @RequestMapping(value = "/talks.json",method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<Talk> talks() {
        return schedule.getSchedule();
    }

    @RequestMapping(value = "/talk/get/{id}.json", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Talk get(@PathVariable int id) {
        return schedule.getTalk(id);
    }

    /**
     * Launched if an error appears
     * @param e Exception raised
     * @return Error message
     */
    @ExceptionHandler
    @ResponseBody
    public Map<String, String> handleException(Exception e) {
        Map<String, String> res = new HashMap<String, String>();
        res.put("error", e.getLocalizedMessage());
        return res;
    }
}