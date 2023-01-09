import com.mike.crud.repository.entity.DeveloperEntity;
import com.mike.crud.repository.entity.SkillEntity;
import com.mike.crud.repository.entity.TestDev;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {

//    private static String url = "jdbc:postgresql://localhost:5432/postgres";
//    private static String userName = "Mike";
//    private static String pwd = "5436";


    public static void main(String[] args) throws SQLException {

//        jdbc:postgresql://host:port/database
//        private static Connection connection = null;
//        Connection connection = DriverManager.getConnection(url, userName, pwd);

//        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
//                .addAnnotatedClass(SpecialtyEntity.class).buildSessionFactory();
//
//        try {
//            Session session = factory.getCurrentSession();
//
//            session.beginTransaction();
//            SpecialtyEntity specialtyEntity = session.get(SpecialtyEntity.class, 1);
//            System.out.println(specialtyEntity.getSpecialty());
//            session.getTransaction().commit();
//        } finally {
//            factory.close();
//        }

//        SpecialtyServicesHib specialtyServicesHib = new SpecialtyServicesHib();
//        System.out.println(specialtyServicesHib.getAllSpecialty());

//        SkillServicesHib skillServicesHib = new SkillServicesHib();
//        System.out.println(skillServicesHib.getAllSkills());



//        Specialty specialty = new Specialty();
//        specialty.setId(1);
//
//        Skill skill = new Skill();
//        skill.setId(1);
//
//        Skill skill1 = new Skill();
//        skill1.setId(4);
//
//        Skill skill2 = new Skill();
//        skill2.setId(5);
//
//
//
//        List<Skill> skillList = new ArrayList<>();
//        skillList.add(skill);
//        skillList.add(skill1);
//        skillList.add(skill2);
//
//        Developer newDeveloper = new Developer();
////        newDeveloper.setId(6);
//        newDeveloper.setFirstName("Oleg");
//        newDeveloper.setLastName("Petrov");
//        newDeveloper.setStatus(Status.ACTIVE);
//        newDeveloper.setSpecialty(specialty);
//        newDeveloper.setSkills(skillList);

//        DeveloperServicesHib developerServicesHib = new DeveloperServicesHib();


//        developerServicesHib.createDeveloper(newDeveloper);
//        developerServicesHib.updateDeveloper(newDeveloper);
//        developerServicesHib.deleteDeveloper(6);

//        System.out.println(developerServicesHib.getDeveloperById(7));
//        Skill skill = new Skill();
//        skill.setSkill("Test99");
//        skill.setStatus(Status.ACTIVE);
//        SkillServicesHib skillServicesHib = new SkillServicesHib();
//        skillServicesHib.createSkill(skill);

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(TestDev.class)
                .addAnnotatedClass(SkillEntity.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            DeveloperEntity dev = session.get(DeveloperEntity.class, 2);

            System.out.println(dev);

            SkillEntity skill1 = session.get(SkillEntity.class, 1);
            SkillEntity skill2 = session.get(SkillEntity.class, 4);
            SkillEntity skill3 = session.get(SkillEntity.class, 5);
            SkillEntity skill4 = session.get(SkillEntity.class, 6);

            List<SkillEntity> newSkill = new ArrayList<>();
            newSkill.add(skill1);
            newSkill.add(skill2);
            newSkill.add(skill3);
            newSkill.add(skill4);

            dev.setSkills(newSkill);

            session.save(dev);

//            List<SkillEntity> getSkill = dev.getListSpec();
//            for(SkillEntity s:getSkill) {
//                System.out.println(s.toString());
//            }

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();

            session.close();
        }



    }
}
