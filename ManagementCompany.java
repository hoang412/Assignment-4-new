package application;
import java.util.*;

public class ManagementCompany extends java.lang.Object {
	
	
	private final int MAX_PROPERTY = 5;
	private double mgmFeePer; 
	private String name;
	private Property[] properties;
	private String taxID;
	private final int MGMT_WIDTH = 10;
	private final int MGMT_DEPTH = 10;
	private Plot plot;
	
	
	 public ManagementCompany() {
		    this.name = "";
		    this.taxID = "";
		    this.mgmFeePer = 0;
		    this.plot = new Plot();
		    this.properties = new Property[MAX_PROPERTY];
		  }

		  public ManagementCompany(String name, String taxID, double mgmFeePer) {
		    this.name = name;
		    this.taxID = taxID;
		    this.mgmFeePer = mgmFeePer;
		    this.plot = new Plot(0,0, MGMT_WIDTH, MGMT_DEPTH);
		    this.properties = new Property[MAX_PROPERTY];
		  }

		  public ManagementCompany(String name, String taxID, double mgmFeePer, 
		      int x, int y, int width, int depth) {
		    this.name = name;
		    this.taxID = taxID;
		    this.mgmFeePer = mgmFeePer;
		    this.plot = new Plot(x,y,width,depth);
		    this.properties = new Property[MAX_PROPERTY];
		  }

		  /**
		   * Adds the property object to the "properties" array.
		   * @param property
		   * @return -1 if the array is full
		   * 			-2 if property is null
		   * 			-3 if the plot is not contained by the MgmtCo plot 
		   * 			-4 of the plot overlaps any other property 
		   * 			 the index in the array where the property was added successfully.

		   */
		  public int addProperty(Property property) {
		    if (property == null) {
		      return -2;
		    }
		    
		    if (!plot.encompasses(property.getPlot())) {
		      return -3;
		    }
		    
		    for (int i = 0;i < properties.length;i++) {
		      if (properties[i] != null) {
		        if (properties[i].getPlot().overlaps(property.getPlot())) {
		          return -4;
		        }
		      } else {
		        properties[i]=property;
		        return i;
		      } 
		    }
		      return -1;
		  }
		  
		  
		  //This method creates a property object and adds it to the "properties" array, in a default plot.
		  public int addProperty(String propertyName, String city, double rent, String ownerName) {
		    return addProperty(new Property(propertyName, city, rent, ownerName));
		  }
		  
		  
		  //This method creates a property object and adds it to the "properties" array.
		  public int addProperty(String propertyName, String city, double rent, String ownerName,
		      int x, int y, int width, int depth) {
		    return addProperty(new Property(propertyName, city, rent, ownerName, x, y, width, depth));
		  }
		  
		  public double totalRent() {
		    double total = 0;
		    for (int i=0;i<properties.length;i++) {
		      if (properties[i]==null) {
		        break;
		      }
		      total += properties[i].getRentAmount();
		    }
		    return total;
		  }
		  
		  /**
		   * This method finds the index of the property with the maximum rent amount. 
		   * NOTE: For simplicity assume that each "Property" object's rent amount is different.
		   * @return index
		   */
		  
		  public int maxRentPropertyIndex() {
		    int index = -1;
		    double max = 0;
		    for (int i = 0;i<properties.length;i++) {
		      if (properties[i]==null) {
		        break;
		      }
		      if (properties[i].getRentAmount() > max) {
		        max = properties[i].getRentAmount();
		        index = i;
		      }
		    }
		    return index;
		  }
		  
		  
		  /**
		   * This method finds the property with the maximum rent amount and returns its toString result. 
		   * NOTE: For simplicity assume that each "Property" object's rent amount is different.
		   * @return maximum Rent as String
		   */
		  public String maxRentProp() {
			  return properties[maxRentPropertyIndex()].toString();
			  }
	
		 
		 /**
		  * Displays the information of the property at index i
		  * @param i
		  * @return information of the property at index i
		  */
		  
		  public String displayPropertyAtIndex(int i) {
		    return properties[i].toString();
		  }
		  
		   
		  /**
		   * Displays the information of all the properties in the "properties" array.
				Overrides:
						toString in class java.lang.Object
						@return information of ALL the properties within this management company by accessing the "Properties" array.
		   */
		  @Override
		  public String toString() {

		    String listOfProperties = "";
		    for (int i = 0; i < MAX_PROPERTY; i++) {
		      if (properties[i]==null) {
		        break;
		      } 
		      listOfProperties += properties[i] + "\n"; 
		    }
		    return "List of the properties for " + name + ", taxID: " + taxID
		        + "\n__________________________________________________\n"
		        + listOfProperties +  "__________________________________________________\n"
		        + "total management Fee: " + (totalRent() * mgmFeePer / 100);
		  }

		  /**
		   * @return the MAX_PROPERTY
		   */
		  public int getMAX_PROPERTY() {
		    return MAX_PROPERTY;
		  }

		  public Plot getPlot() {
		    return plot;
		  }

		  public String getName() {
		    return name;
		  }

		  
		}