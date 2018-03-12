Evan Elkin

For this project, I utilized old code from previous projects that I 
completed to help with all of the I/O portions. After that, I used a
basic algorithm based off of the pseudocode in the assignment sheet for
the unique elements checker. Finally, I utilized a 
selection sort algorithm that we discussed in class
so that the checker would work, as it only works if the array 
is sorted first.


1. The time complexity of the unique elements algorithm would be equal to Θ(n^2). 
	The reasoning behind this is because the algorithm contains a nested for loop,
	which is generally indicative of an n^2, or quadratic, order of growth. In addition, n^2 is
	the highest order of growth in this algorithm.
2. One way that may improve efficiency is to have a comparative if statement 
	that compares i to i+1 instead of a for loop for j. In addition, reduce the
	max value of the for loop by 1 so there's no out of bounds error.
	