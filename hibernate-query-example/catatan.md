## Hibernate Query Language

**Delete**
```
String hql = "DELETE FROM Employee WHERE id = :employee_id";
Query query = session.createQuery(hql);
query.setParameter("employee_id", 10);
int result = query.executeUpdate();
```

**Update**
```
String hql = "UPDATE Employee set salary = :salary WHERE id = :employee_id";
Query query = session.createQuery(hql);
query.setParameter("salary", 1000);
query.setParameter("employee_id", 10);
int result = query.executeUpdate();
```

**Positional parameters**

```
Query query = session.createQuery("from Student where studentId = ? and studentName=?");
query.setInteger(0, 1).setString(1, "Jack");
```

**Select**
```
String hql = "FROM Employee";
Query query = session.createQuery(hql);
List results = query.list();

String hql = "FROM com.hibernatebook.criteria.Employee";
Query query = session.createQuery(hql);
List results = query.list();

String hql = "FROM Employee AS E";
Query query = session.createQuery(hql);
List results = query.list();

String hql = "FROM Employee E";
Query query = session.createQuery(hql);
List results = query.list();

String hql = "from Category";
Query query = session.createQuery(hql);
List<Category> listCategories = query.list();

String hql = "from Product where category.name = 'Computer'";
Query query = session.createQuery(hql);
List<Product> listProducts = query.list();

String hql = "from Product where description like :keyword";
String keyword = "New";
Query query = session.createQuery(hql);
query.setParameter("keyword", "%" + keyword + "%");
List<Product> listProducts = query.list();

Query query = session.createQuery("from Karyawan where id = :id");
query.setLong("id", id);
Karyawan karyawan = (Karyawan) query.uniqueResult();

from Product where price >= 500 and price <= 1000
```

**Aggregate Methods**

* avg
* count
* max
* min
* sum
* count(distinct…)

```
Query query = session.createQuery("select max(gaji) from Karyawan");
BigDecimal max = (BigDecimal) query.uniqueResult();

String hql = "select count(name) from Product";
Query query = session.createQuery(hql);
List listResult = query.list();
Number number = (Number) listResult.get(0);
System.out.println(number.intValue());
```

**Join Query**

* inner join
* left outer join
* right outer join
* full join

```
String hql = "from Product p inner join p.category";
Query query = session.createQuery(hql);
List<Object[]> listResult = query.list();
for (Object[] aRow : listResult) {
    Product product = (Product) aRow[0];
    Category category = (Category) aRow[1];
    System.out.println(product.getName() + " - " + category.getName());
}

from Product p inner join p.category with p.price > 500
```

**Sort Query**

```
String hql = "from Product order by price ASC";
Query query = session.createQuery(hql);
List<Product> listProducts = query.list()
```

**Group By Query**

```
String hql = "select sum(p.price), p.category.name from Product p group by category";
Query query = session.createQuery(hql);
List<Object[]> listResult = query.list();
for (Object[] aRow : listResult) {
    Double sum = (Double) aRow[0];
    String category = (String) aRow[1];
    System.out.println(category + " - " + sum);
}
```

**Pagination Query**

```
String hql = "from Product";
Query query = session.createQuery(hql);
query.setFirstResult(0);
query.setMaxResults(10);
List<Product> listProducts = query.list();
```

**Date Range Query**

```
String hql = "from Order where purchaseDate >= :beginDate and purchaseDate <= :endDate";
Query query = session.createQuery(hql);
SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
Date beginDate = dateFormatter.parse("2016-11-01");
query.setParameter("beginDate", beginDate);
Date endDate = dateFormatter.parse("2016-11-22");
query.setParameter("endDate", endDate);
List<Order> listOrders = query.list();
```

### Hibernate Criteria

**Criteria restrictions query**

* Restrictions.eq -> equals `=`
* Restrictions.lt -> less than `<`
* Restrictions.le -> less than or equal `<=`
* Restrictions.gt -> great than (>)
* Restrictions.ge -> great than or equal `>=`
* Restrictions.like
* Restrictions.between
* Restrictions.isNull
* Restrictions.isNotNull

```
Criteria criteria = session.createCriteria(StockDailyRecord.class)
    .add(Restrictions.eq("volume", 10000));

Criteria criteria = session.createCriteria(StockDailyRecord.class)
   .add(Restrictions.lt("volume", 10000));

Criteria criteria = session.createCriteria(StockDailyRecord.class)
   .add(Restrictions.le("volume", 10000));

Criteria criteria = session.createCriteria(StockDailyRecord.class)
   .add(Restrictions.gt("volume", 10000));

Criteria criteria = session.createCriteria(StockDailyRecord.class)
   .add(Restrictions.ge("volume", 10000));

Criteria criteria = session.createCriteria(StockDailyRecord.class)
   .add(Restrictions.like("stockName", "MKYONG%"));

Criteria criteria = session.createCriteria(StockDailyRecord.class)
   .add(Restrictions.between("date", startDate, endDate));

Criteria criteria = session.createCriteria(StockDailyRecord.class)
   .add(Restrictions.isNull("volume"));

Criteria criteria = session.createCriteria(StockDailyRecord.class)
   .add(Restrictions.isNotNull("volume"));

Crietria c = session.createCriteria(Emp.class);  
c.addOrder(Order.asc("salary"));  

List cats = sess.createCriteria(Cat.class)
    .add( Restrictions.like("name", "Fritz%") )
    .add( Restrictions.between("weight", minWeight, maxWeight) )
    .list();

List cats = sess.createCriteria(Cat.class)
    .add( Restrictions.like("name", "Fritz%") )
    .add( Restrictions.or(
        Restrictions.eq( "age", new Integer(0) ),
        Restrictions.isNull("age")
    ) )
    .list();

List cats = sess.createCriteria(Cat.class)
    .add( Restrictions.in( "name", new String[] { "Fritz", "Izi", "Pk" } ) )
    .add( Restrictions.disjunction()
        .add( Restrictions.isNull("age") )
        .add( Restrictions.eq("age", new Integer(0) ) )
        .add( Restrictions.eq("age", new Integer(1) ) )
        .add( Restrictions.eq("age", new Integer(2) ) )
    ) )
    .list();

List cats = sess.createCriteria(Cat.class)
    .add( Restrictions.sqlRestriction("lower({alias}.name) like lower(?)", "Fritz%", Hibernate.STRING) )
    .list();

Property age = Property.forName("age");
List cats = sess.createCriteria(Cat.class)
    .add( Restrictions.disjunction()
        .add( age.isNull() )
        .add( age.eq( new Integer(0) ) )
        .add( age.eq( new Integer(1) ) )
        .add( age.eq( new Integer(2) ) )
    ) )
    .add( Property.forName("name").in( new String[] { "Fritz", "Izi", "Pk" } ) )
    .list();

List cats = sess.createCriteria(Cat.class)
    .add( Restrictions.like("name", "F%")
    .addOrder( Order.asc("name") )
    .addOrder( Order.desc("age") )
    .setMaxResults(50)
    .list();

List cats = sess.createCriteria(Cat.class)
    .add( Property.forName("name").like("F%") )
    .addOrder( Property.forName("name").asc() )
    .addOrder( Property.forName("age").desc() )
    .setMaxResults(50)
    .list();

List cats = sess.createCriteria(Cat.class)
    .add( Restrictions.like("name", "F%") )
    .createCriteria("kittens")
        .add( Restrictions.like("name", "F%") )
    .list();

List cats = sess.createCriteria(Cat.class)
    .createAlias("kittens", "kt")
    .createAlias("mate", "mt")
    .add( Restrictions.eqProperty("kt.name", "mt.name") )
    .list();

List results = session.createCriteria(Cat.class)
    .setProjection( Projections.projectionList()
        .add( Projections.rowCount() )
        .add( Projections.avg("weight") )
        .add( Projections.max("weight") )
        .add( Projections.groupProperty("color") )
    )
    .list();
	
List results = session.createCriteria(Cat.class)
    .setProjection( Projections.alias( Projections.groupProperty("color"), "colr" ) )
    .addOrder( Order.asc("colr") )
    .list();

List results = session.createCriteria(Cat.class)
    .setProjection( Projections.projectionList()
        .add( Projections.rowCount(), "catCountByColor" )
        .add( Projections.avg("weight"), "avgWeight" )
        .add( Projections.max("weight"), "maxWeight" )
        .add( Projections.groupProperty("color"), "color" )
    )
    .addOrder( Order.desc("catCountByColor") )
    .addOrder( Order.desc("avgWeight") )
    .list();
```

