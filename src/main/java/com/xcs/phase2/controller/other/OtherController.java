package com.xcs.phase2.controller.other;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.other.OtherDAO;
import com.xcs.phase2.dao.other.RemainProductDAO;
import com.xcs.phase2.model.other.*;
import com.xcs.phase2.request.other.*;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.Other.RemainProductinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OtherController {

    private static final Logger log = LoggerFactory.getLogger(OtherController.class);

    @Autowired
    private OtherDAO otherDAO;

    @Autowired
    private RemainProductDAO remainProductDAO;

    @PostMapping(value = "/CountOffenseOfZoneByTime")
    public ResponseEntity CountOffenseOfZoneByTime(@RequestBody CountOffenseOfZoneByTimeReq req) {

        log.info("============= Start API CountOffenseOfZoneByTime ================");
        MessageResponse msg = new MessageResponse();
        List<CountOffenseOfZone> res = null;
        Boolean checkType = true;
        try {

            res = otherDAO.CountOffenseOfZoneByTime(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API CountOffenseOfZoneByTime =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/CountOffenseOfAreaByZone")
    public ResponseEntity CountOffenseOfAreaByZone(@RequestBody CountOffenseOfAreaByZoneReq req) {

        log.info("============= Start API CountOffenseOfAreaByZone ================");
        MessageResponse msg = new MessageResponse();
        List<CountOffenseOfArea> res = null;
        Boolean checkType = true;
        try {

            res = otherDAO.CountOffenseOfAreaByZone(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API CountOffenseOfAreaByZone =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/LawbreakeNetworkgetByCon")
    public ResponseEntity LawbreakeNetworkgetByCon(@RequestBody LawbreakeNetworkgetByConReq req) {

        log.info("============= Start API LawbreakeNetworkgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<LawbreakeNetwork> res = null;
        Boolean checkType = true;
        try {

            res = otherDAO.LawbreakeNetworkgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API LawbreakeNetworkgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/TimeLineListgetByCon")
    public ResponseEntity TimeLineListgetByCon(@RequestBody TimeLineListgetByConReq req) {

        log.info("============= Start API TimeLineListgetByCon ================");
        MessageResponse msg = new MessageResponse();
        List<TimeLineList> res = null;
        Boolean checkType = true;
        try {

            res = otherDAO.TimeLineListgetByCon(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API TimeLineListgetByCon =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/RemainProductinsAll")
    public ResponseEntity RemainProductinsAll(@RequestBody RemainProduct req) {

        log.info("============= Start API RemainProductinsAll ================");
        RemainProductinsAllResponse res = null;
        try {

            res = remainProductDAO.RemainProductinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API RemainProductinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/LawsuitRunningLawsuitNo")
    public ResponseEntity LawsuitRunningLawsuitNo(@RequestBody RunningNoReq req) {


        log.info("============= Start API LawsuitRunningLawsuitNo ================");
        MessageResponse msg = new MessageResponse();
        Running res = null;
        Boolean checkType = true;
        try {

            res = otherDAO.LawsuitRunningLawsuitNo(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API LawsuitRunningLawsuitNo =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/ProveRunningProveNo")
    public ResponseEntity ProveRunningProveNo(@RequestBody RunningNoReq req) {


        log.info("============= Start API ProveRunningProveNo ================");
        MessageResponse msg = new MessageResponse();
        Running res = null;
        Boolean checkType = true;
        try {

            res = otherDAO.ProveRunningProveNo(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API ProveRunningProveNo =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }

    @PostMapping(value = "/CompareRunningCompareNo")
    public ResponseEntity CompareRunningCompareNo(@RequestBody RunningNoReq req) {


        log.info("============= Start API CompareRunningCompareNo ================");
        MessageResponse msg = new MessageResponse();
        Running res = null;
        Boolean checkType = true;
        try {

            res = otherDAO.CompareRunningCompareNo(req);

        } catch (Exception e) {
            checkType = false;
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());

        }
        log.info("============= End API CompareRunningCompareNo =================");
        return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
    }
}
