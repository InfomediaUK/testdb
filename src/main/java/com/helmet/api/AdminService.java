package com.helmet.api;

import java.sql.Date;
import java.util.List;

import com.helmet.bean.AdminAccess;
import com.helmet.bean.AdminAccessGroup;
import com.helmet.bean.AdminAccessGroupEntity;
import com.helmet.bean.AdminAccessGroupItem;
import com.helmet.bean.Administrator;
import com.helmet.bean.AdministratorAccess;
import com.helmet.bean.AdministratorAccessGroup;
import com.helmet.bean.AdministratorAccessGroupUser;
import com.helmet.bean.AdministratorAccessUser;
import com.helmet.bean.AdministratorEntity;
import com.helmet.bean.Agency;
import com.helmet.bean.AgencyUserEntity;
import com.helmet.bean.AgyAccess;
import com.helmet.bean.AgyAccessGroup;
import com.helmet.bean.AgyAccessGroupEntity;
import com.helmet.bean.AgyAccessGroupItem;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BudgetTransaction;
import com.helmet.bean.BudgetTransactionUser;
import com.helmet.bean.Client;
import com.helmet.bean.ClientAgencyJobProfile;
import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.ClientAgencyJobProfileUserEntity;
import com.helmet.bean.ClientReEnterPwd;
import com.helmet.bean.ClientUserEntity;
import com.helmet.bean.CompliancyTest;
import com.helmet.bean.Consultant;
import com.helmet.bean.ConsultantAccess;
import com.helmet.bean.ConsultantAccessGroup;
import com.helmet.bean.ConsultantAccessGroupUser;
import com.helmet.bean.ConsultantAccessUser;
import com.helmet.bean.ConsultantEntity;
import com.helmet.bean.Country;
import com.helmet.bean.IntValue;
import com.helmet.bean.VisaType;
import com.helmet.bean.IdDocument;
import com.helmet.bean.DisciplineCategory;
import com.helmet.bean.DisciplineCategoryUserEntity;
import com.helmet.bean.DisciplineCategoryUserEntityAdmin;
import com.helmet.bean.AreaOfSpeciality;
import com.helmet.bean.EmailAction;
import com.helmet.bean.GeographicalRegion;
import com.helmet.bean.JobFamily;
import com.helmet.bean.JobFamilyEntity;
import com.helmet.bean.JobProfile;
import com.helmet.bean.JobProfileGrade;
import com.helmet.bean.JobProfileUserEntity;
import com.helmet.bean.JobSubFamily;
import com.helmet.bean.JobSubFamilyEntity;
import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.ReEnterPwd;
import com.helmet.bean.RecordCount;
import com.helmet.bean.Regulator;
import com.helmet.bean.TrainingCourse;
import com.helmet.bean.TrainingCompany;
import com.helmet.bean.TrainingCompanyUserEntity;

public interface AdminService extends CommonService {

    // login
  public Administrator validateLogin(Administrator administrator);
  public Administrator validateSecretWord(Administrator administrator);
    public int updateAdministratorPwd(Integer administratorId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId);
    public int updateAdministratorSecretWord(Integer administratorId, String newSecretWord, Integer noOfChanges, Integer auditorId);

  public List<Administrator> getAdministratorAudits();
  public List<Administrator> getAdministrators(boolean showOnlyActive);
  public Administrator getAdministrator(Integer administratorId);
  public AdministratorEntity getAdministratorEntity(Integer administratorId);
  public int insertAdministrator(Administrator administrator, Integer auditorId);
  public int updateAdministrator(Administrator administrator, Integer auditorId);
  public int deleteAdministrator(Integer administratorId, Integer noOfChanges, Integer auditorId);

  public Country getCountry(Integer countryId);
  public int insertCountry(Country country, Integer auditorId);
  public int updateCountry(Country country, Integer auditorId);
  public int deleteCountry(Integer countryId, Integer noOfChanges, Integer auditorId);
// NEW -->
  public int insertEmailAction(EmailAction emailAction, Integer auditorId);
  public int updateEmailAction(EmailAction emailAction, Integer auditorId);
  public int deleteEmailAction(Integer emailActionId, Integer noOfChanges, Integer auditorId);
  public EmailAction getEmailAction(Integer emailActionId);
  // <-- NEW
  // NEW -->
  public List<Applicant> getApplicantsToCopy(Integer sourceAgencyId, Integer targetAgencyId);
  public List<Applicant> getApplicantsForIdDocument(Integer idDocumentId); 
  public Applicant getApplicant(Integer applicantId);
  public int insertApplicant(Applicant applicant, Integer auditorId);
  public NhsBackingReport getNhsBackingReport(Integer nhsBackingReportId);
  // <-- NEW
  public int insertGeographicalRegion(GeographicalRegion geographicalRegion, Integer auditorId);
  public int updateGeographicalRegion(GeographicalRegion geographicalRegion, Integer auditorId);
  public int deleteGeographicalRegion(Integer geographicalRegionId, Integer noOfChanges, Integer auditorId);

  public int insertAreaOfSpeciality(AreaOfSpeciality areaOfSpeciality, Integer auditorId);
  public int updateAreaOfSpeciality(AreaOfSpeciality areaOfSpeciality, Integer auditorId);
  public int deleteAreaOfSpeciality(Integer areaOfSpecialityId, Integer noOfChanges, Integer auditorId);

  public List<com.helmet.xml.jaxb.model.DisciplineCategory> getJerseyDisciplineCategories(boolean showOnlyActive); 
  public com.helmet.xml.jaxb.model.DisciplineCategory getJerseyDisciplineCategory(Integer disciplineCategoryId);
  public DisciplineCategoryUserEntity getDisciplineCategoryUserEntity(Integer disciplineCategoryId, boolean showOnlyActive);
  public DisciplineCategoryUserEntityAdmin getDisciplineCategoryUserEntityAdmin(Integer disciplineCategoryId, boolean showOnlyActive);
  public DisciplineCategory getDisciplineCategoryForCode(String code);
  public DisciplineCategory getDisciplineCategoryForName(String name);
  public int insertDisciplineCategory(DisciplineCategory disciplineCategory, Integer auditorId);
  public int updateDisciplineCategory(DisciplineCategory disciplineCategory, Integer auditorId);
  public int updateDisciplineCategoryDisplayOrder(DisciplineCategory disciplineCategory, Integer auditorId);
  public int deleteDisciplineCategory(Integer disciplineCategoryId, Integer noOfChanges, Integer auditorId);

  public List<com.helmet.xml.jaxb.model.VisaType> getJerseyVisaTypes(boolean showOnlyActive); 
  public com.helmet.xml.jaxb.model.VisaType getJerseyVisaType(Integer visaTypeId);
  public VisaType getVisaType(Integer visaTypeId);
  public VisaType getVisaTypeForCode(String code);
  public VisaType getVisaTypeForName(String name);
  public int insertVisaType(VisaType visaType, Integer auditorId);
  public int updateVisaType(VisaType visaType, Integer auditorId);
  public int updateVisaTypeDisplayOrder(VisaType visaType, Integer auditorId);
  public int deleteVisaType(Integer visaTypeId, Integer noOfChanges, Integer auditorId);

  public List<com.helmet.xml.jaxb.model.IdDocument> getJerseyIdDocuments(boolean showOnlyActive); 
  public com.helmet.xml.jaxb.model.IdDocument getJerseyIdDocument(Integer idDocumentId);
  public IdDocument getIdDocumentForCode(String code);
  public IdDocument getIdDocumentForName(String name);
  public int insertIdDocument(IdDocument idDocument, Integer auditorId);
  public int updateIdDocument(IdDocument idDocument, Integer auditorId);
  public int updateIdDocumentDisplayOrder(IdDocument idDocument, Integer auditorId);
  public int deleteIdDocument(Integer idDocumentId, Integer noOfChanges, Integer auditorId);

  public List<com.helmet.xml.jaxb.model.Agency> getJerseyAgencies(boolean showOnlyActive); 
  public com.helmet.xml.jaxb.model.Agency getJerseyAgency(Integer agencyId);

  public List<ReEnterPwd> getReEnterPwds(boolean showOnlyActive);
  public ReEnterPwd getReEnterPwd(Integer reEnterPwdId);
  public int insertReEnterPwd(ReEnterPwd reEnterPwd, Integer auditorId);
  public int updateReEnterPwd(ReEnterPwd reEnterPwd, Integer auditorId);
  public int deleteReEnterPwd(Integer reEnterPwdId, Integer noOfChanges, Integer auditorId);

  public List<AdminAccess> getAdminAccesses(boolean showOnlyActive);
  public List<AdminAccess> getActiveAdminAccessesForAdministrator(Integer administratorId);
  public AdminAccess getAdminAccess(Integer adminAccessId);
  public int insertAdminAccess(AdminAccess adminAccess, Integer auditorId);
  public int updateAdminAccess(AdminAccess adminAccess, Integer auditorId);
  public int deleteAdminAccess(Integer adminAccessId, Integer noOfChanges, Integer auditorId);

  public List<AdminAccessGroup> getAdminAccessGroups(boolean showOnlyActive);
  public AdminAccessGroup getAdminAccessGroup(Integer adminAccessGroupId);
  public AdminAccessGroupEntity getAdminAccessGroupEntity(Integer adminAccessGroupId);
  public int insertAdminAccessGroup(AdminAccessGroup adminAccessGroup, Integer auditorId);
  public int updateAdminAccessGroup(AdminAccessGroup adminAccessGroup, Integer auditorId);
  public int deleteAdminAccessGroup(Integer adminAccessGroupId, Integer noOfChanges, Integer auditorId);
  
  public int insertAdminAccessGroupItem(AdminAccessGroupItem adminAccessGroupItem, Integer auditorId);
  public int deleteAdminAccessGroupItem(Integer adminAccessGroupItemId, Integer noOfChanges, Integer auditorId);

  public List<ClientUserEntity> getClientUserEntities(boolean showOnlyActive);
  // getClientUser moved to common service
  public Client getClient(Integer clientId);
  public List<Client> getClientsForTradeshiftSbsPayablesCode(String tradeshiftSbsPayablesCode);
  public ClientUserEntity getClientUserEntity(Integer clientId);
  public ClientUserEntity getClientUserEntity(Integer clientId, boolean showOnlyActive);
  public int insertClient(Client client, Integer auditorId);
  // update and getClientUser moved to common service
  public int deleteClient(Integer clientId, Integer noOfChanges, Integer auditorId);
  public int updateClientDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId);

  public Agency getAgency(Integer agencyId);
  // update and getAgencyUser moved to common service
  public Consultant getProspectConsultant(Integer agencyId);
  public AgencyUserEntity getAgencyUserEntity(Integer agencyId, boolean showOnlyActive);
  public int insertAgency(Agency agency, Integer auditorId);
  public int deleteAgency(Integer agencyId, Integer noOfChanges, Integer auditorId);
  public int updateAgencyDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId);

  public List<AdministratorAccessUser> getAdministratorAccessUsersForAdministrator(Integer administratorId);
  public int insertAdministratorAccess(AdministratorAccess administratorAccess, Integer auditorId);
  public int deleteAdministratorAccess(Integer administratorAccessId, Integer noOfChanges, Integer auditorId);
  
  public List<AdministratorAccessGroupUser> getAdministratorAccessGroupUsersForAdministrator(Integer administratorId);
  public int insertAdministratorAccessGroup(AdministratorAccessGroup administratorAccessGroup, Integer auditorId);
  public int deleteAdministratorAccessGroup(Integer administratorAccessGroupId, Integer noOfChanges, Integer auditorId);
  
  public List<JobFamily> getJobFamiliesForClient(Integer clientId, boolean showOnlyActive);
  public JobFamily getJobFamily(Integer jobFamilyId);
  public JobFamilyEntity getJobFamilyEntity(Integer jobFamilyId, boolean showOnlyActive);
  public int insertJobFamily(JobFamily jobFamily, Integer auditorId);
  public int updateJobFamily(JobFamily jobFamily, Integer auditorId);
  public int deleteJobFamily(Integer jobFamilyId, Integer noOfChanges, Integer auditorId);
  public int updateJobFamilyDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId);

  public List<JobSubFamily> getJobSubFamiliesForJobFamily(Integer jobFamilyId, boolean showOnlyActive);
  public JobSubFamily getJobSubFamily(Integer jobSubFamilyId);
  public JobSubFamilyEntity getJobSubFamilyEntity(Integer jobSubFamilyId, boolean showOnlyActive);
  public int insertJobSubFamily(JobSubFamily jobSubFamily, Integer auditorId);
  public int updateJobSubFamily(JobSubFamily jobSubFamily, Integer auditorId);
  public int deleteJobSubFamily(Integer jobSubFamilyId, Integer noOfChanges, Integer auditorId);
  public int updateJobSubFamilyDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId);
  
  public List<JobProfile> getJobProfilesForJobSubFamily(Integer jobSubFamilyId, boolean showOnlyActive);
  public JobProfile getJobProfile(Integer jobProfileId);
  public JobProfileUserEntity getJobProfileUserEntity(Integer jobProfileId);
  public int insertJobProfile(JobProfile jobProfile, Integer auditorId);
  public int updateJobProfile(JobProfile jobProfile, Integer auditorId);
  public int deleteJobProfile(Integer jobProfileId, Integer noOfChanges, Integer auditorId);
  public int updateJobProfileDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId);

  public int insertJobProfileGrade(JobProfileGrade jobProfileGrade, Integer auditorId);
  public int deleteJobProfileGrade(Integer jobProfileGradeId, Integer noOfChanges, Integer auditorId);

  public int insertClientReEnterPwd(ClientReEnterPwd clientReEnterPwd, Integer auditorId);
  public int deleteClientReEnterPwd(Integer clientReEnterPwdId, Integer noOfChanges, Integer auditorId);
  
  public ClientAgencyJobProfile getClientAgencyJobProfile(Integer clientAgencyJobProfileId);
  public int insertClientAgencyJobProfile(ClientAgencyJobProfile clientAgencyJobProfile, Integer auditorId);
  public int updateClientAgencyJobProfile(ClientAgencyJobProfile clientAgencyJobProfile, Integer auditorId);
  public int deleteClientAgencyJobProfile(Integer clientAgencyJobProfileId, Integer noOfChanges, Integer auditorId);

  public ClientAgencyJobProfileUserEntity getClientAgencyJobProfileUserEntity(Integer clientAgencyJobProfileId);

  public ClientAgencyJobProfileGrade getClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId);
  public int insertClientAgencyJobProfileGrade(ClientAgencyJobProfileGrade clientAgencyJobProfileGrade, Integer auditorId);
  public int updateClientAgencyJobProfileGrade(ClientAgencyJobProfileGrade clientAgencyJobProfileGrade, Integer auditorId);
  public int deleteClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId, Integer noOfChanges, Integer auditorId);

  public List<Consultant> getConsultantsForAgency(Integer agencyId, boolean showOnlyActive);
  public Consultant getConsultant(Integer consultantId);
  public ConsultantEntity getConsultantEntity(Integer consultantId);
  public int insertConsultant(Consultant consultant, Integer auditorId);
  public int updateConsultant(Consultant consultant, Integer auditorId);
  public int deleteConsultant(Integer consultantId, Integer noOfChanges, Integer auditorId);

    public int updateConsultantPwd(Integer consultantId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId);
    public int updateConsultantSecretWord(Integer consultantId, String newSecretWord, Integer noOfChanges, Integer auditorId);

  public List<AgyAccess> getAgyAccesses(boolean showOnlyActive);
  public List<AgyAccess> getActiveAgyAccessesForConsultant(Integer consultantId);
  public AgyAccess getAgyAccess(Integer mgrAccessId);
  public int insertAgyAccess(AgyAccess mgrAccess, Integer auditorId);
  public int updateAgyAccess(AgyAccess mgrAccess, Integer auditorId);
  public int deleteAgyAccess(Integer mgrAccessId, Integer noOfChanges, Integer auditorId);

  public List<AgyAccessGroup> getAgyAccessGroupsForAgency(Integer agencyId, boolean showOnlyActive);
  public AgyAccessGroup getAgyAccessGroup(Integer agyAccessGroupId);
  public AgyAccessGroupEntity getAgyAccessGroupEntity(Integer agyAccessGroupId);
  public int insertAgyAccessGroup(AgyAccessGroup agyAccessGroup, Integer auditorId);
  public int updateAgyAccessGroup(AgyAccessGroup agyAccessGroup, Integer auditorId);
  public int deleteAgyAccessGroup(Integer agyAccessGroupId, Integer noOfChanges, Integer auditorId);
  
  public int insertAgyAccessGroupItem(AgyAccessGroupItem agyAccessGroupItem, Integer auditorId);
  public int deleteAgyAccessGroupItem(Integer agyAccessGroupItemId, Integer noOfChanges, Integer auditorId);

  public List<ConsultantAccessUser> getConsultantAccessUsersForConsultant(Integer consultantId);
  public int insertConsultantAccess(ConsultantAccess consultantAccess, Integer auditorId);
  public int deleteConsultantAccess(Integer consultantAccessId, Integer noOfChanges, Integer auditorId);
  
  public List<ConsultantAccessGroupUser> getConsultantAccessGroupUsersForConsultant(Integer consultantId);
  public int insertConsultantAccessGroup(ConsultantAccessGroup consultantAccessGroup, Integer auditorId);
  public int deleteConsultantAccessGroup(Integer consultantAccessGroupId, Integer noOfChanges, Integer auditorId);

  public List<BudgetTransactionUser> getBudgetTransactionUsersForClient(Integer clientId, boolean showOnlyActive);
  public BudgetTransaction getBudgetTransaction(Integer budgetTransactionId);
  public int insertBudgetTransaction(BudgetTransaction budgetTransaction, Integer auditorId);
//  public int updateBudgetTransaction(BudgetTransaction budgetTransaction, Integer auditorId);
  public int deleteBudgetTransaction(Integer budgetTransactionId, Integer noOfChanges, Integer auditorId);

  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAdmin(Date fromDate, Date toDate);
  
  public IntValue getActiveBookingGradeApplicantsCountForClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId);
  
  public Regulator getRegulator(Integer regulatorId);
  public Regulator getRegulatorForCode(String code);
  public Regulator getRegulatorForName(String name);
  public int insertRegulator(Regulator regulator, Integer auditorId);
  public int updateRegulator(Regulator regulator, Integer auditorId);
  public int updateRegulatorDisplayOrder(Regulator regulator, Integer auditorId);
  public int deleteRegulator(Integer regulatorId, Integer noOfChanges, Integer auditorId);

  public CompliancyTest getCompliancyTest(Integer regulatorId);
  public CompliancyTest getCompliancyTestForProperty(String name);
  public int insertCompliancyTest(CompliancyTest regulator, Integer auditorId);
  public int updateCompliancyTest(CompliancyTest regulator, Integer auditorId);
  public int updateCompliancyTestDisplayOrder(CompliancyTest regulator, Integer auditorId);
  public int deleteCompliancyTest(Integer regulatorId, Integer noOfChanges, Integer auditorId);
  
  public TrainingCompany getTrainingCompany(Integer trainingCompanyId);
  public TrainingCompanyUserEntity getTrainingCompanyUserEntity(Integer agencyId, boolean showOnlyActive);
  public int insertTrainingCompany(TrainingCompany trainingCompany, Integer auditorId);
  public int updateTrainingCompany(TrainingCompany trainingCompany, Integer auditorId);
  public int deleteTrainingCompany(Integer trainingCompanyId, Integer noOfChanges, Integer auditorId);
  public int updateTrainingCompanyDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId);

  public TrainingCourse getTrainingCourseForCode(String code);
  public TrainingCourse getTrainingCourseForName(String name);
  public int insertTrainingCourse(TrainingCourse trainingCourse, Integer auditorId);
  public int updateTrainingCourse(TrainingCourse trainingCourse, Integer auditorId);
  public int updateTrainingCourseDisplayOrder(TrainingCourse trainingCourse, Integer auditorId);
  public int deleteTrainingCourse(Integer trainingCourseId, Integer noOfChanges, Integer auditorId);

  public RecordCount getActiveApplicantTrainingCourseCountForTrainingCourse(Integer trainingCourseId);

}
