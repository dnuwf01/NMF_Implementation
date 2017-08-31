Non Negative Matrix Factorisation is a popular algorithm in multivariate analysis and linear algebra. In this algorithm a matrix 
V is factorized into two lower order matrices based on an arbitrary rank decided by the user. The factorized matrices should have 
no negative values. The non-negativity of the matrices makes it easier to analyze.

NMF finds wide applications in computer vision, document clustering, topic modelling anf recommender systems.



Let matrix V be the product of the matrices W and H,

V= WH



When multiplying matrices, the dimensions of the factor matrices may be significantly lower than those of the product matrix and it is this property that forms the basis of NMF. NMF generates factors with significantly reduced dimensions compared to the original matrix. For example, if V is an m × n matrix, W is an m × p matrix, and H is a p × n matrix then p can be significantly less than both m and n.

Here's an example based on a text-mining application:

1. Let the input matrix (the matrix to be factored) be V with 10000 rows and 500 columns where words are in rows and documents are in columns. That is, we have 500 documents indexed by 10000 words. It follows that a column vector v in V represents a document.
2. Assume we ask the algorithm to find 10 features in order to generate a features matrix W with 10000 rows and 10 columns and a coefficients matrix H with 10 rows and 500 columns.
3. The product of W and H is a matrix with 10000 rows and 500 columns, the same shape as the input matrix V and, if the factorization worked, it is a reasonable approximation to the input matrix V.
4. From the treatment of matrix multiplication above it follows that each column in the product matrix WH is a linear combination of the 10 column vectors in the features matrix W with coefficients supplied by the coefficients matrix H.

This last point is the basis of NMF because we can consider each original document in our example as being built from a small set of hidden features. NMF generates these features.

It's useful to think of each feature (column vector) in the features matrix W as a document archetype comprising a set of words where each word's cell value defines the word's rank in the feature: The higher a word's cell value the higher the word's rank in the feature. A column in the coefficients matrix H represents an original document with a cell value defining the document's rank for a feature. We can now reconstruct a document (column vector) from our input matrix by a linear combination of our features (column vectors in W) where each feature is weighted by the feature's cell value from the document's column in H.


Lastly, NMF has an inherent clustering property, as it automatically clusters the columns of input data V = (v1,v2,v3).
It is this property that drives most applications of NMF.

min ||V-WH|| = 0