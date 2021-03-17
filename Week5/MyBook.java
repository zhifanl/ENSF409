public class MyBook{




    public abstract class Book{
        private String isbn;
        private int publicationYear;
        private int pages;
        public Book(){}

        public Book(String isbn, int pages){
            this.isbn=isbn;
            this.pages=pages;
        }
    }
    public abstract class Hardcover extends Book{
        public abstract void binding();
    }

    public abstract class Paperback extends Book{
        public abstract String coverArt();
    }
    public class Classic extends Hardcover{
        private int origPubYear=1860;
        private Author theAuthor;
        private Publisher[] bookPublisher;

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
        public void setBookPublisher(Publisher[] bookPublisher) {
        	this.bookPublisher=bookPublisher;
        }
        public String createNotes(){
            return "Method createNotes called from Classic";
        }
    }
    public class Publisher{
    	private String name;
        private String address;
        private Classic[] classicCatalog;
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
        public Classic[] getClassicCatalog(){
            return classicCatalog;
        }
        public void setName(String name){
            this.name=name;
        }
        public void setAddress(String address){
            this.address=address;
        }
        public void setClassicCatalog(Classic[]class)
    }
}