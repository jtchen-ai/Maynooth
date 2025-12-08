#include "stdafx.h" 
#include <iostream> 
using namespace std; 
 
// Create a class (object) called Sphere, this is a 
// description of the object, not an instance of the object. 
// It is the cookie cutter not the cookie. 
class Sphere 
{ 
public: float radius; 
public: float surface_area(); 
 
 // Class constructor runs when a new instance of "sphere" is created. 
 Sphere() 
 { 
 radius=1.0f; 
 } 
 
 // In line function belonging sphere 
 float diameter() 
 { 
 return(2.0f*radius); 
 } 
}; 
 
// External method used by sphere 
float Sphere::surface_area() 
{ 
 float area=4.0f*3.14159*(radius*radius); 
 return(area); 
} 
 
 
int _tmain(int argc, _TCHAR* argv[]) 
{ 
 class Sphere ball1; // Create an instance of Sphere called ball1 (a cookie!) 
 class Sphere *ball2; // Create a pointer to store the location of an instance of Sphere 
 
 ball1.radius=10; // Make the radius of ball1 = 10 
 
 ball2=new Sphere(); // Create a new instance of a sphere and assign its location to ball2 
 
 ball2->radius=10; // Make ball2 radius equal to 10 
 
 (*ball2).radius=10; // This is the same as the line above, you can dereference a 
 // pointer and then use the dot to access members, (*p). same as -> 
 
 cout << ball1.radius << "," << ball2->radius; // Print value of radius in ball1 and ball2 
 
 int x; cin >> x; // Wait for keypress 
} 