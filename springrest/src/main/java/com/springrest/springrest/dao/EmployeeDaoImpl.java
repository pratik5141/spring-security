package com.springrest.springrest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.springrest.springrest.config.CustomUserDetails;
import com.springrest.springrest.config.UserDetailsServiceImpl;
import com.springrest.springrest.entities.BankAccount;
import com.springrest.springrest.entities.Company;
import com.springrest.springrest.entities.Employee;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao{

	
	  @Autowired 
	  private SessionFactory sessionFactory;
	  
	  @Autowired
	  private BCryptPasswordEncoder passwordEncoder; 
	  
		/*
		 * @Autowired private UserDetails loggedUser;
		 */	  
	  public void setSessionFactory(SessionFactory sessionFactory){
	  this.sessionFactory = sessionFactory; }
	 
//	@Autowired
//	 private SessionFactory sessionFactory;//=new Configuration().configure().buildSessionFactory();
//	 
	/*
	 * public EmployeeDaoImpl(SessionFactory sessionFactory) { this.sessionFactory =
	 * sessionFactory; }
	 */
	 @Autowired
	private EntityManager entityManager;
	
	BankAccount b = new BankAccount();
	
	@Autowired
    public EmployeeDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;

    }
	 
	@Autowired
	BankAccountDao bd;
	// Session session = this.sessionFactory.getCurrentSession();
	 
	
	 
	@Override
	public List<Employee> findAll() {
		/*
		CriteriaBuilder builder=s.getCriteriaBuilder();
	    CriteriaQuery<Employee>	criteriaQuery = builder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);
		criteriaQuery.select(root);
		Predicate employeePredicate = builder.equal(root.get("role"), "ROLE_NORMAL");
		
		criteriaQuery.where(employeePredicate);
		list  = s.createQuery(criteriaQuery).list();
		
		
		Criteria c = s.createCriteria(Employee.class);
	    c.createAlias("bankAccount","account");
	    
	   list = c.addOrder(Order.asc("name")).list();
	   */
	   
	   
		/*Projection p1 = Projections.property("salaty");
		
		Projection p2 = Projections.property("empId");
		ProjectionList p = Projections.projectionList();
		p.add(p1);
		p.add(p2);
		Projection p = Projections.max("account.balance");
		c.setProjection(p);
	    long result =  (long) c.uniqueResult();
	    System.out.println("result is::"+result);
	     */
	    //DetachedCriteria query = DetachedCriteria.forClass(Employee.class)
		  // .setProjection(Property.forName("role"));
		
		/*DetachedCriteria query = DetachedCriteria.forClass(Employee.class)
				.add(Property.forName("name").eq("Jay"));
		  */       
	    Session s = sessionFactory.openSession();
	   // Transaction txn = s.beginTransaction();
		
		//@SuppressWarnings("deprecation")
		
		  Criteria c = s.createCriteria(Employee.class);
		  
		  c.createAlias("company", "company");
		  c.createAlias("bankAccount","bankAccount");
		  c.add(Restrictions.eq("role","ROLE_NORMAL"));
		  //c.add(Subqueries.eq("transcationType", query));
		 // c.add(Restrictions.eq("company.companyName","Easy Pay"));
		  //c.add(Restrictions.("salaty", 19000));
		 // c.add(Restrictions.eq("bankAccount.transcationType","DEBIT"));
		 
		List<Employee> list =c.list();//query.getExecutableCriteria(s).list();// // 
		
		System.out.println(list);
	//	txn.commit();
	
		
		//---------------------
		
		/*
		 * CriteriaBuilder cb = s.getCriteriaBuilder(); CriteriaQuery<Long> query
		 * =cb.createQuery(Long.class); Root<Employee> root1 =
		 * query.from(Employee.class); query.multiselect(cb.avg(root1.get("salaty")));
		 * System.out.println(s.createQuery(query).getSingleResult());
		 */ 
	 	s.close();		
		return list;	
	}

	
	@Override
	public Employee findById(int empId) {
		
		//System.out.println(csd.getUsername());
		
		Session s = sessionFactory.openSession();
		Criteria c = s.createCriteria(Employee.class);
		c.add(Restrictions.eq("empId", empId));
		Employee e = (Employee) c.uniqueResult();
		s.close();
		return e;
	}

	@Override
	public void deleteById(int empId) {
		// TODO Auto-generated method stub
		/*
		 * Query query = entityManager.createQuery("delete from Employee where id:eId");
		 * query.setParameter("eId",empId); query.executeUpdate();
		 */
		/*
		 * Session currentSession = entityManager.unwrap(Session.class); Employee e =
		 * entityManager.find(Employee.class, empId); //int bid =
		 * e.getBankAccount().getAccountId(); //e.setBankAccount(e.getBankAccount()); //
		 * delete object with primary key Query theQuery = currentSession.createQuery(
		 * "delete from Employee where id=:employeeId");
		 * 
		 * theQuery.setParameter("employeeId", empId); theQuery.executeUpdate();
		 */
	
		Session currentSession = sessionFactory.openSession();
		Transaction txn = currentSession.beginTransaction();
		Query query =currentSession.createQuery("delete from Employee where id=:emp_id");
		query.setParameter("emp_id", empId);
		query.executeUpdate();
		txn.commit();
		currentSession.close();
	}

	
	public Employee addEmployee(Employee newEmployee) {
		/*
		 * Session currentSession = entityManager.unwrap(Session.class); b =
		 * e.getBankAccount(); //System.out.println("Inside EmployeeDao"+e.toString());
		 * e.setBankAccount(b); currentSession.save(e);
		 * currentSession.close();
		 */
		
		Session currentSession = sessionFactory.openSession();
		Transaction tx = currentSession.beginTransaction();
		newEmployee.setPassword(passwordEncoder.encode(newEmployee.getPassword()));
		currentSession.saveOrUpdate(newEmployee);
		tx.commit();
		currentSession.close(); 
		return newEmployee;
	}

	@Override
	public Employee updateEmployee(Employee e) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(e);
		tx.commit();
		session.close();
		return e;
	}

	@Override
	public Employee getUserByEmail(String email) {
		Employee employee=sessionFactory.getCurrentSession().find(Employee.class, email);
		System.out.println("User login credentials"+employee.getEmailId()+employee.getPassword());
		return employee;
	}

}
