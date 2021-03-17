/**
 * @author Zhifan Li
 *
 */




//class Book
     abstract class Book{
        private String isbn;
        private int publicationYear;
        private int pages;
        public Book(){}

        public Book(String isbn, int pages){
            this.isbn=isbn;
            this.pages=pages;
        }

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public int getPublicationYear() {
			return publicationYear;
		}

		public void setPublicationYear(int publicationYear) {
			this.publicationYear = publicationYear;
		}

		public int getPages() {
			return pages;
		}

		public void setPages(int pages) {
			this.pages = pages;
		}
    }
//class Hardcover
     abstract class Hardcover extends Book{
		public Hardcover(String isbn, int pages) {
			super(isbn, pages);
		}
		public Hardcover() {
			super();
		}
        public abstract void binding();
    }
    
//class Paperback
     abstract class Paperback extends Book{
		public Paperback(String isbn, int pages) {
			super(isbn, pages);
		}
		public Paperback() {
			super();
		}
        public  String coverArt() {
        	return "Method coverArt called from Paperback";
		}
    }
 //class Classic
       class Classic extends Hardcover{
        private int origPubYear=1860;
        private Author theAuthor;
        private Publisher[] bookPublisher=new Publisher[1];
		public Classic(String isbn, int pages) {
			super(isbn, pages);
		}
		public Classic() {
			super();
		}
        public int getOrigPubYear() {
        	return origPubYear;
        }
        public Author getTheAuthor() {
        	return theAuthor;
        }
        public Publisher[] getBookPublisher() {
        	return bookPublisher;
        }
        public void setOrigPubYear(int origPubYear) {
        	this.origPubYear=origPubYear;
        }
        public void setTheAuthor(Author theAuthor) {
        	this.theAuthor=theAuthor;
        }
        public void setBookPublisher(Publisher[] bookPublisher) throws Exception{
        	if(bookPublisher.length < 1) {
				throw new Exception("Instance of Classic class requires a minimum of 1 Publisher");
			}
			this.bookPublisher=bookPublisher;
        }
        public String createNotes(){
            return "Method createNotes called from Classic";
        }
		public void binding() {
			
			
		}
    }
 // class Publisher
     class Publisher{
    	private String name;
        private String address;
        private Classic[] classicsCatalog=null;
        public Publisher(String name, String address){
            this.name=name;
            this.address=address;
        }
        public String printLetterhead(){
            return "Method printLetterhead called from Publisher";
        }
        public String getName(){
            return name;
        }
        public String getAddress(){
            return address;
        }
        public Classic[] getClassicsCatalog(){
            return classicsCatalog;
        }
        public void setName(String name){
            this.name=name;
        }
        public void setAddress(String address){
            this.address=address;
        }
        public void setClassicsCatalog(Classic[] classCatalog) {
        	this.classicsCatalog=classCatalog;
        }
    }
   //class Author
     class Author {
    	private String name="Unknown";
    	private String address;
    	private int age;
    	
		public Author(String name, String address, int age) {
			
			this.name = name;
			this.address = address;
			this.age = age;
		}
		
		public Author() {
			
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String write() {
			return "Method write called from Author";
		}
    	
    }
    //class Fiction
     abstract class Fiction extends Paperback{
    	public abstract String coverArt();
		public Fiction(String isbn, int pages) {
			super(isbn, pages);
		}
		public Fiction() {
			super();
		}
    	public String genre() {
    		return "Method genre called from Fiction";
    	}
    }
    //class Nonfiction
     class Nonfiction extends Paperback{
    	private Category deweyClassification=new Category();
		public Nonfiction(String isbn, int pages) {
			super(isbn, pages);
		}
		public Nonfiction() {
			super();
		}
    	public String topic() {
    		return "Method topic called from Nonfiction";

    	}
		public Category getDeweyClassification() {
			return deweyClassification;
		}
		public void setDeweyClassification(Category deweyClassification) {
			this.deweyClassification = deweyClassification;
		}
    	
    }
    //class Category
     class Category{
    	private Category subCategory;
    	private Category superCategory;
    	private String category;
    	public String sort() {
    		return "Method sort called from Category";
    	}
		public Category getSubCategory() {
			return subCategory;
		}
		public void setSubCategory(Category subCategory) {
			this.subCategory = subCategory;
		}
		public Category getSuperCategory() {
			return superCategory;
		}
		public void setSuperCategory(Category superCategory) {
			this.superCategory = superCategory;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
    	
    }
    //class Story
     class Story{
    	private Author theAuthor=new Author();
    	public String plot() {
    		return "Method plot called from Story";
    	}
		public Author getTheAuthor() {
			return theAuthor;
		}
		public void setTheAuthor(Author theAuthor) {
			this.theAuthor = theAuthor;
		}
    }
    //class Anthology
     class Anthology extends Fiction{
    	private Story[] story=new Story[5];
		public Anthology(String isbn, int pages) {
			super(isbn, pages);
		}
		public Anthology() {
			super();
		}
		
    	public Story[] getStory() {
			return story;
		}
		public void setStory(Story[] story) throws Exception {
			if(story.length < 5) {
				throw new Exception("An instance of class Anthology requires a minimum of 5 Stories");}
			this.story = story;
		}
		public String storyOrder() {
    		return "Method storyOrder called from Anthology";
    	}
    	public String coverArt() {
    		return "Method coverArt called from Anthology";
    	}
    	
    }
    //class Novel
     class Novel extends Fiction{
    	private Author theAuthor=new Author();
    	private Series mySeries=new Series();
		public Novel(String isbn, int pages) {
			super(isbn, pages);
		}
		public Novel() {
			super();
		}
    	public String theme() {
    		return "Method theme called from Novel";
    	}
    	public String coverArt() {
    		return "Method coverArt called from Novel";
    	}
		public Author getTheAuthor() {
			return theAuthor;
		}
		public void setTheAuthor(Author theAuthor) {
			this.theAuthor = theAuthor;
		}
		public Series getMySeries() {
			return mySeries;
		}
		public void setMySeries(Series mySeries) {
			this.mySeries = mySeries;
		}
    	
    }
    // class Series
     class Series{
    	private String seriesName;
    	public String theme() {
        		return "Method theme called from Series";
        	}
		public String getSeriesName() {
			return seriesName;
		}
		public void setSeriesName(String seriesName) {
			this.seriesName = seriesName;
		}
    	
    	}
    //class Contract
     class Contract{
    	private String date;
    	private Publisher publisherInfo;
    	private Author authorInfo;
		public Contract(String date,Publisher publisherInfo, Author authorInfo) {
			this.date = date;
			this.publisherInfo = publisherInfo;
			this.authorInfo = authorInfo;
		}
		public String printContract() {
	    		return "Method printContract called from Contract";
	    	}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public Publisher getPublisherInfo() {
			return publisherInfo;
		}
		public void setPublisherInfo(Publisher publisherInfo) {
			this.publisherInfo = publisherInfo;
		}
		public Author getAuthorInfo() {
			return authorInfo;
		}
		public void setAuthorInfo(Author authorInfo) {
			this.authorInfo = authorInfo;
		}
		
		}
		public class MyBook{

    	public static void main(String[] args) {
    	
		var history = new Nonfiction();
		var deweyOne = new Category();
		deweyOne.setCategory("History");
		var deweyTwo = new Category();
		deweyTwo.setCategory("French");
		deweyTwo.setSuperCategory(deweyOne);
		deweyOne.setSubCategory(deweyTwo);
		var deweyThree = new Category();
		deweyThree.setCategory("Revolution");
		deweyThree.setSuperCategory(deweyTwo);
		deweyTwo.setSubCategory(deweyThree);
		System.out.println(deweyThree.sort());
		history.setDeweyClassification(deweyThree);
		System.out.println(history.topic());
		System.out.println(history.coverArt());

		// Series, Novel
		var lestat = new Series();
		lestat.setSeriesName("Lestat the Vampire");
		System.out.println(lestat.theme());
		var interviewWithVampire = new Novel();
		interviewWithVampire.setMySeries(lestat);
		//interviewWithVampire.setTheAuthor(anne);
		System.out.println(interviewWithVampire.coverArt());
		System.out.println(interviewWithVampire.genre());

		// Anthology, Story
		var vampireInParis = new Story();
		//vampireInParis.setTheAuthor(anne);
		System.out.println(vampireInParis.plot());
		var vampiresEverywhere = new Anthology();
		System.out.println(vampiresEverywhere.storyOrder());

			Classic classic=new Classic();
			System.out.println(classic.createNotes());
			classic.binding();
			System.out.println(classic.getBookPublisher()[0]);
			
			Publisher publisher=new Publisher("name","address");
			System.out.println(publisher.printLetterhead());
			Author author=new Author();
			System.out.println(author.write());
			Story story=new Story();
			System.out.println(story.plot());
			Series series=new Series();
			System.out.println(series.theme());


			Novel novel=new Novel();
			System.out.println(novel.theme());
			System.out.println(novel.genre());
			System.out.println(novel.coverArt());

			Anthology anthology=new Anthology();
			System.out.println(anthology.storyOrder());
			System.out.println(anthology.genre());
			System.out.println(anthology.coverArt());

			Nonfiction nonfiction=new Nonfiction();
			System.out.println(nonfiction.topic());
			System.out.println(nonfiction.coverArt());
			System.out.println(nonfiction.getIsbn()+nonfiction.getPublicationYear()+nonfiction.getPages());

			Category category=new Category();
			System.out.println(category.sort());
			

			Contract contract=new Contract("data",publisher,author);
			System.out.println(contract.printContract());



			
    	}
    }
    
    
