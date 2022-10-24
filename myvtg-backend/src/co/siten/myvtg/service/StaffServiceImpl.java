package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.dao.AccountDao;
import co.siten.myvtg.dao.AgentDao;
import co.siten.myvtg.model.apigw.Account;
import co.siten.myvtg.model.myvtg.Agent;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ExcelExporter;
import co.siten.myvtg.util.ResponseUtil;

import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static co.siten.myvtg.controller.BaseController.DES_FAIL;

@Service("StaffService")
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
@PropertySource(value = {"classpath:config.properties"})
public class StaffServiceImpl implements StaffService {

    @Autowired
    private AgentDao agentDao;

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    private AccountDao accountDao;

    private static final String DES_SUC = "su.des.suc.";

    @Override
    public BaseResponseBean getStaffs(RequestBean request, String language) {
        try {
            Map<String, Object> wsRequest = request.getWsRequest();
            String search = DataUtil.nullOrValueS(wsRequest.get("search"));
            Integer page = DataUtil.nullOrValueI(wsRequest.get("page"));
            Integer size = DataUtil.nullOrValueI(wsRequest.get("size"));
            String sortBy = DataUtil.nullOrValueS(wsRequest.get("sortBy"));
            String sortType = DataUtil.nullOrValueS(wsRequest.get("sortType"));
            if (DataUtil.isNullOrEmpty(page) || DataUtil.isNullOrEmpty(size)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.parameter.error.", language);
            }
            page = page - 1;
            if (page < 0) page = Constants.CUSTOMER_PAGE_START;
            if (size <= 0) size = Constants.CUSTOMER_RECORD_PER_PAGE;
            Map<String, Object> listStaff = agentDao.getListStaff(search, size, page, sortBy, sortType);
            if (DataUtil.isNullOrEmpty(listStaff)) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.get.", language, listStaff);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean addStaff(RequestBean requestBean, String language) {
        try {
            Map<String, Object> wsRequest = requestBean.getWsRequest();
            Date date = agentDao.getTime();
            String fullName = DataUtil.nullOrValueS(wsRequest.get("fullName"));
            String phoneNumber = DataUtil.nullOrValueS(wsRequest.get("phoneNumber"));
            String employeeCode = DataUtil.nullOrValueS(wsRequest.get("employeeCode"));
            Integer employeeType = DataUtil.nullOrValueI(wsRequest.get("employeeType"));
            String position = DataUtil.nullOrValueS(wsRequest.get("position"));
            String department = DataUtil.nullOrValueS(wsRequest.get("department"));
            String showroomName = DataUtil.nullOrValueS(wsRequest.get("showroomName"));
            String showroomAddress = DataUtil.nullOrValueS(wsRequest.get("showroomAddress"));
            String emoneyAccountNumber = DataUtil.nullOrValueS(wsRequest.get("emoneyAccountNumber"));
            Integer status = DataUtil.nullOrValueI(wsRequest.get("status"));
            String note = DataUtil.nullOrValueS(wsRequest.get("note"));
            Agent agent;
            if (DataUtil.isNullOrEmpty(phoneNumber)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            Account account = accountDao.findByPhoneNumber(phoneNumber);
            if (account == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            agent = agentDao.findByPhoneNumber(phoneNumber);
            if (agent == null) {
                agent = new Agent();
            }
            agent.setCamId(account.getId());
            agent.setFullName(fullName);
            agent.setPhoneNumber(phoneNumber);
            agent.setEmployeeCode(employeeCode);
            agent.setEmployeeType(employeeType);
            agent.setPosition(position);
            agent.setDepartment(department);
            agent.setShowroomName(showroomName);
            agent.setShowroomAddress(showroomAddress);
            agent.setEmoneyAccountNumber(emoneyAccountNumber);
            agent.setNote(note);
            agent.setStatus(status);
            agent.setCreatedTime(date);
            agent.setLastUpdatedTime(date);
            agentDao.insert(agent);
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.create.update.", language);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean deleteStaff(RequestBean requestBean, String language) {
        try {
            Map<String, Object> wsRequest = requestBean.getWsRequest();
            Long id = DataUtil.nullOrValueL(wsRequest.get("id"));
            if (id != null) {
                Agent agent = agentDao.findById(id);
                agent.setStatus(Constants.DELETED);
                agentDao.insert(agent);
            }
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.create.update.", language);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean getStaffByPhone(RequestBean request, String language) {
        try {
            Map<String, Object> wsRequest = request.getWsRequest();
            String phoneNumber = DataUtil.nullOrValueS(wsRequest.get("phoneNumber"));
            Map<String, Object> listStaff = agentDao.getStaffByPhone(phoneNumber);
            if (DataUtil.isNullOrEmpty(listStaff)) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.get.", language, listStaff);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }
    @Override
    public List<Agent> csvImport(InputStream is) {
        List<Agent> listAgents = agentDao.getAll();
        List<Agent> result = new ArrayList<>();
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                String phoneNumber = data[1];
                Agent agent = agentDao.findByPhoneNumber(phoneNumber);
                if (DataUtil.isNullOrEmpty(agent)) {
                    agent = new Agent();
                }
                agent = setData(agent, Long.valueOf(data[0]), data[1],
                        data[2], Integer.valueOf(data[3]), data[4],
                        data[5], data[6], data[7], Integer.valueOf(data[8]),
                        data[9]);
                result.add(agent);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        listAgents.removeIf(x -> result.contains(x));
        for (Agent temp : listAgents) {
            temp.setStatus(Constants.DELETED);
            agentDao.insert(temp);
        }
        return result;
    }

    @Override
    public BaseResponseBean excelImport(MultipartFile multipartFile, String language) {
        try {
            List<Agent> listAgents = agentDao.getAll();
            List<Agent> result = new ArrayList<>();
            try {
                Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
                if (DataUtil.isNullOrEmpty(multipartFile.getInputStream())) {
                    return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
                }
                if (DataUtil.isNullOrEmpty(workbook)) {
                    return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
                }
                for (Sheet sheet : workbook) {
                    for (Row row : sheet) {
                        String phoneNumber = row.getCell(1).getStringCellValue();
                        Agent agent = agentDao.findByPhoneNumber(phoneNumber);
                        if (DataUtil.isNullOrEmpty(agent)) {
                            agent = new Agent();
                        }
                        agent = setData(agent, Long.valueOf(row.getCell(0).getStringCellValue()), phoneNumber,
                                row.getCell(2).getStringCellValue(), Integer.valueOf(row.getCell(3).getStringCellValue()), row.getCell(4).getStringCellValue(),
                                row.getCell(5).getStringCellValue(), row.getCell(6).getStringCellValue(), row.getCell(7).getStringCellValue()
                                , Integer.valueOf(row.getCell(8).getStringCellValue()),
                                row.getCell(9).getStringCellValue());
                        result.add(agent);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            listAgents.removeIf(x -> result.contains(x));
            for (Agent temp : listAgents) {
                temp.setStatus(Constants.DELETED);
                agentDao.insert(temp);
            }
            if (DataUtil.isNullOrEmpty(result)) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.get.", language, result);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean csvImport(MultipartFile multipartFile, String language) {
        try {
            if (DataUtil.isNullOrEmpty(multipartFile)) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            List<Agent> result = csvImport(multipartFile.getInputStream());
            if (DataUtil.isNullOrEmpty(result)) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.get.", language, result);
        } catch (
                Exception ex) {
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean excelExport(String language, HttpServletResponse response, RequestBean request) {
        try {
            Map<String, Object> wsRequest = request.getWsRequest();
            String search = DataUtil.nullOrValueS(wsRequest.get("search"));
            String sortBy = DataUtil.nullOrValueS(wsRequest.get("sortBy"));
            String sortType = DataUtil.nullOrValueS(wsRequest.get("sortType"));
            List<Agent> result = agentDao.getListStaffs(search, sortBy, sortType);
            if (DataUtil.isNullOrEmpty(result)) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            ExcelExporter excelExporter = new ExcelExporter(result);
            OutputStream outputStream =  excelExporter.export(response);
            if (DataUtil.isNullOrEmpty(outputStream)) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.get.", language, outputStream);
        } catch (Exception ex) {
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    public Agent setData(Agent agent, Long camId, String phoneNumber,
                         String employeeCode, Integer employeeType, String position,
                         String department, String showroomName, String showroomAddress, Integer status,
                         String fullName) {
        agent.setCamId(camId);
        agent.setPhoneNumber(phoneNumber);
        agent.setEmployeeCode(employeeCode);
        agent.setEmployeeType(employeeType);
        agent.setPosition(position);
        agent.setDepartment(department);
        agent.setShowroomName(showroomName);
        agent.setShowroomAddress(showroomAddress);
        agent.setStatus(status);
        agent.setFullName(fullName);
        agentDao.insert(agent);
        return agent;
    }


}
