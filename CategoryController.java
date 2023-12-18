package bvic.apps.scorecards.controller;

import bvic.apps.scorecards.dao.AssesmentDao;
import bvic.apps.scorecards.dao.SegmentDao;
import bvic.apps.scorecards.model.MdLSize;
import bvic.apps.scorecards.security.SessionUser;
import bvic.apps.scorecards.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    SegmentDao segmentDao;
    @Autowired
    AssesmentDao assesmentDao;
    @Autowired
    SessionUser sessionUser;

    @GetMapping({ "/HomeCategory" })
    public String HomeCategory(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            model.addAttribute("loggedinUserName", sessionUser.getLoggedinUserName());
            model.addAttribute("loggedinRoleId", sessionUser.getLoggedinUserRoleId());
            String dateToday = FormatUtil.dateToStringFormat(new Date(), "yyyyMMdd");
            model.addAttribute("dateToday", FormatUtil.dateStringFormat(dateToday, "yyyyMMdd", "dd/MM/yyyy"));
            return "Level/HomeCategory";
        }
        return "redirect:/home";
    }

    @GetMapping({ "/ViewSegmentLevel" })
    public String ViewSegmentLevel(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            model.addAttribute("loggedinUserName", sessionUser.getLoggedinUserName());
            model.addAttribute("loggedinRoleId", sessionUser.getLoggedinUserRoleId());
            String dateToday = FormatUtil.dateToStringFormat(new Date(), "yyyyMMdd");
            model.addAttribute("dateToday", FormatUtil.dateStringFormat(dateToday, "yyyyMMdd", "dd/MM/yyyy"));

            model.addAttribute("segmentlevel", segmentDao.ListTrxSegment());
            model.addAttribute("segmentasm", assesmentDao.ListMasterAssesment());

            return "Level/ViewSegmentLevel";
        }
        return "redirect:/home";
    }

    @GetMapping({ "/SegmentLevel" })
    public String SegmentLevel(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            model.addAttribute("loggedinUserName", sessionUser.getLoggedinUserName());
            model.addAttribute("loggedinRoleId", sessionUser.getLoggedinUserRoleId());
            String dateToday = FormatUtil.dateToStringFormat(new Date(), "yyyyMMdd");
            model.addAttribute("dateToday", FormatUtil.dateStringFormat(dateToday, "yyyyMMdd", "dd/MM/yyyy"));

            List<MdLSize> positionSize = segmentDao.ListSize();
            model.addAttribute("positionSize", positionSize);
            model.addAttribute("stu", segmentDao.ListSize());

            model.addAttribute("segmentasm", assesmentDao.ListMasterAssesment());

            return "Level/SegmentLevel";
        }
        return "redirect:/home";
    }

    @GetMapping({ "/EditSegmentLevel/{branchSize}" })
    public String EditSegmentLevel(Model model, @PathVariable String branchSize) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            model.addAttribute("loggedinUserName", sessionUser.getLoggedinUserName());
            model.addAttribute("loggedinRoleId", sessionUser.getLoggedinUserRoleId());
            String dateToday = FormatUtil.dateToStringFormat(new Date(), "yyyyMMdd");
            model.addAttribute("dateToday", FormatUtil.dateStringFormat(dateToday, "yyyyMMdd", "dd/MM/yyyy"));

            model.addAttribute("segmentlevel", segmentDao.ListSegmentById(branchSize));
            model.addAttribute("segmentasm", assesmentDao.ListMasterAssesment());

            List<MdLSize> positionSize = segmentDao.ListSize();
            model.addAttribute("positionSize", positionSize);
            model.addAttribute("stu", segmentDao.ListSize());

            return "Level/EditSegmentLevel";
        }
        return "redirect:/home";
    }

    @GetMapping("/DeleteListTrxSegment")
    public String DeleteListTrxSegment(@RequestParam String branchSize) {
        segmentDao.DeleteListTrxSegment(branchSize);
        return "redirect:/ViewSegmentLevel";
    }

    // BM

    @GetMapping({ "/ViewSegmentLevelBM" })
    public String ViewSegmentLevelMO(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            model.addAttribute("loggedinUserName", sessionUser.getLoggedinUserName());
            model.addAttribute("loggedinRoleId", sessionUser.getLoggedinUserRoleId());
            String dateToday = FormatUtil.dateToStringFormat(new Date(), "yyyyMMdd");
            model.addAttribute("dateToday", FormatUtil.dateStringFormat(dateToday, "yyyyMMdd", "dd/MM/yyyy"));

            model.addAttribute("segmentlevel", segmentDao.ListTrxSegment());
            model.addAttribute("segmentasm", assesmentDao.ListMasterAssesment());

            return "Level/ViewSegmentLevelBM";
        }
        return "redirect:/home";
    }

    // RO

    // @GetMapping({ "/ViewSegmentLevelRO" })
    // public String ViewSegmentLevelRO(Model model) {
    //     SecurityContext context = SecurityContextHolder.getContext();
    //     Authentication authentication = context.getAuthentication();
    //     if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
    //         model.addAttribute("loggedinUserName", sessionUser.getLoggedinUserName());
    //         model.addAttribute("loggedinRoleId", sessionUser.getLoggedinUserRoleId());
    //         String dateToday = FormatUtil.dateToStringFormat(new Date(), "yyyyMMdd");
    //         model.addAttribute("dateToday", FormatUtil.dateStringFormat(dateToday, "yyyyMMdd", "dd/MM/yyyy"));

    //         model.addAttribute("segmentlevel", segmentDao.ListTrxSegment());
    //         model.addAttribute("segmentasm", assesmentDao.ListMasterAssesment());

    //         return "Level/ViewSegmentLevelRO";
    //     }
    //     return "redirect:/home";
    // }
}

/*
 * TEMPORARY
 * 
 * @GetMapping("/HomeCategory")
 * public String HomeCategory(Model model)
 * {
 * return "Level/HomeCategory";
 * }
 * 
 * @GetMapping("/ViewSegmentLevel")
 * public ModelAndView ViewSegmentLevel(Model model){
 * ModelAndView mav = new ModelAndView("Level/ViewSegmentLevel");
 * mav.addObject("segmentlevel", segmentDao.ListTrxSegment());
 * mav.addObject("segmentasm", assesmentDao.ListMasterAssesment());
 * return mav;
 * }
 * 
 * @GetMapping({"/SegmentLevel"})
 * public ModelAndView SegmentLevel(Model model){
 * ModelAndView mav = new ModelAndView("Level/SegmentLevel");
 * List<MdLSize> positionSize = segmentDao.ListSize();
 * model.addAttribute("positionSize", positionSize);
 * model.addAttribute("stu", segmentDao.ListSize() );
 * mav.addObject("segmentasm", assesmentDao.ListMasterAssesment());
 * return mav;
 * }
 * 
 * @GetMapping("/EditSegmentLevel/{branchSize}")
 * public ModelAndView getTrxSegment(Model model, @PathVariable String
 * branchSize){
 * ModelAndView mav = new ModelAndView("Level/EditSegmentLevel");
 * mav.addObject("segmentlevel", segmentDao.ListSegmentById(branchSize));
 * mav.addObject("segmentasm", assesmentDao.ListMasterAssesment());
 * List<MdLSize> positionSize = segmentDao.ListSize();
 * model.addAttribute("positionSize", positionSize);
 * model.addAttribute("stu", segmentDao.ListSize() );
 * return mav;
 * }
 * 
 * 
 * 
 * END TEMPORARY
 */