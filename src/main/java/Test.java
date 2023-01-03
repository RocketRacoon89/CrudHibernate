import com.mike.crud.model.Developer;
import com.mike.crud.model.Skill;
import com.mike.crud.model.Specialty;
import com.mike.crud.model.Status;
import com.mike.crud.repository.entity.SkillEntity;
import com.mike.crud.repository.entity.SpecialtyEntity;
import com.mike.crud.servicesHibernate.DeveloperServicesHib;
import com.mike.crud.servicesHibernate.SkillServicesHib;
import com.mike.crud.servicesHibernate.SpecialtyServicesHib;
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

        DeveloperServicesHib developerServicesHib = new DeveloperServicesHib();
//        developerServicesHib.createDeveloper(newDeveloper);
//        developerServicesHib.updateDeveloper(newDeveloper);
//        developerServicesHib.deleteDeveloper(6);

        System.out.println(developerServicesHib.getDeveloperById(7));
//        Skill skill = new Skill();
//        skill.setSkill("Test99");
//        skill.setStatus(Status.ACTIVE);
//        SkillServicesHib skillServicesHib = new SkillServicesHib();
//        skillServicesHib.createSkill(skill);

    }
}
