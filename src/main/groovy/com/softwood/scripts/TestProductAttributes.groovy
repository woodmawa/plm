package com.softwood.scripts

import com.softwood.portfolio.AttributeGroup
import com.softwood.portfolio.AttributeValueListItem
import com.softwood.portfolio.NamedListOfValues
import com.softwood.portfolio.Product
import com.softwood.portfolio.ProductAttribute
import com.softwood.portfolio.ProductAttributeAssignment
import com.softwood.portfolio.ProductClass

//setup a product
ProductClass pclass = new ProductClass (productClass: "node", productType: "Phone")
Product product = new Product (code: "123", name:"iPhone", SKU:"ph/123", productClass:pclass)

//setup an attributeGroup

AttributeGroup attGroup = new AttributeGroup (groupName:"Dimensions")

//set up some attributes for group

def gattList = []
ProductAttribute pAttrib = new ProductAttribute (dataType: String, name:"width", partOfAttributeGroups:attGroup)
attGroup.add (pAttrib); gattList << pAttrib

pAttrib = new ProductAttribute (dataType: String, name:"depth", partOfAttributeGroups:attGroup)
attGroup.add (pAttrib);gattList << pAttrib

pAttrib = new ProductAttribute (dataType: String, name:"height", partOfAttributeGroups:attGroup)
attGroup.add (pAttrib); gattList << pAttrib

//set up shape lov for one of attributes in dimensions group
NamedListOfValues shapeLov = new NamedListOfValues(lovName:"shape")
shapeLov << new AttributeValueListItem (sequenceNumber:1, value:"square")
shapeLov << new AttributeValueListItem (sequenceNumber:2, value:"rectangle", isDefaultValue:true)

pAttrib = new ProductAttribute (dataType: String, name:"shape", partOfAttributeGroups:attGroup, hasLoV:true)
//add using optional named lov instance
attGroup.add (pAttrib, shapeLov); gattList << pAttrib


//set up some standalone attributes

//setup an attribute which has an assoc lov

def lovAttList = []
pAttrib = new ProductAttribute (dataType: String, name:"colour", hasLoV:true)
lovAttList << pAttrib

//now setup the named Lov
NamedListOfValues colourLov = new NamedListOfValues(lovName:"colour")
colourLov << new AttributeValueListItem (sequenceNumber:1, value:"red")  //dont really need to add at inverse side
colourLov << new AttributeValueListItem (sequenceNumber:2, value:"blue")
colourLov << new AttributeValueListItem (sequenceNumber:3, value:"gold", isDefaultValue:true)
colourLov << new AttributeValueListItem (sequenceNumber:4, value:"silver", owningNamedLov: colourLov)

def singletonAttList = []
pAttrib = new ProductAttribute (dataType: String, name:"weight", hasLoV:false)
singletonAttList << pAttrib

//now assign the things to mapping

ProductAttributeAssignment mapping = new ProductAttributeAssignment()
mapping.product =  product
mapping.addAttributeGroup(attGroup) //add dimensions group
mapping.addProductAttribute(lovAttList[0], colourLov) // add standalone attribute with defined LoV
mapping.addProductAttribute (singletonAttList[0])  //no lov here

println mapping.toString()