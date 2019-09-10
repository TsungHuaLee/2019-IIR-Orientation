# 2019_IIR_Summer_Orientation_HW
# Python_HW
## Homework A
1. Tell the difference of the two files: iris.csv and iris_output.csv
2. How two save the dataframe to iris_output.csv exactly the same with iris.csv
## Answer A
1. pandas read_csv() 除了會將原有的column讀入之外，會多加入一個unique index column，直接使用 pandas to_csv() 會將unique index column 一併寫出csv檔。
如下圖所示：<br>
iris.csv：
![](https://i.imgur.com/tgUm9jU.png)<br>
iris_output.csv：
![](https://i.imgur.com/1rZioKT.png)

2. 欲在寫出時忽略unique index column，可以設定參數 ==index=False==。
如下圖所示：
  ![](https://i.imgur.com/4sABuSz.png)

## Homework B 
### Why we should avoid using itertuples? Design a simple function to work on one line Compare the difference in speed between apply and itertuples
## Answer B
itertuples()execution time: 12ms
apply(axis=0) execution time: 6.78ms
apply(axis=1) execution time: 78.6ms

apply的效率和計算時事看哪一個軸有關係。

## Homewrork C
### Write a context manger named "prf_plot" to plot precision/recall/F1

F1 = 2

1. 宣告大量隨機分佈在0-1之間的precision（x 座標），帶入上方公式，計算f1 ＝ 0.1, 0.2, ... , 0.9的情況下，相對應的recall(y座標)。

2. 只取 0.0 < Recall < 1.0的範圍。
如不處理，matplotlib會將兩個極值得部份連起：
![](https://i.imgur.com/6T9fTxi.png)

# Word2Vec homework
74.0847737789154s

word2vec parameter
* size: Dimensionality of the word vectors.
* window: Maximum distance between the current and predicted word within a sentence.
* min_count: Ignores all words with total frequency lower than this.
* workers: use threads to train model.(Cython)

### How to estimate the time used on your computer to train with full English Wikipedia
wiki have 3654 million words

1. using heap's law estimate volcabulary size
    $V_R(n) = K \times n^\beta$

    Setting parameter
    $K = 50$
    $\beta = 0.5$

    $V_R(n) = 50 \times (3.654 \times 10^9) ^ {0.5} = 3022416.3$

2. using CBOW estimate model related factor
    $Q = N \times D + D \times log_2(V)$
    N = window size for input
    D = dimension of p layer
    V = size of valcabulary (不重複的單字數)

    Setting parameter
    N = 5
    D = 100
    V = 3022416.3
    
    $Q = 5 \times 100 + 100 \times log_2(3022416.3) = 2653$

3. calculate word2vec time complexity
    $O = E \times T \times Q$
    E = epoch
    T = word in corpus
    Q = model related factor (CBOW and skip-gram)
    
    Setting parameter
    E = 1
    T = 3.654 G
    Q = 2653 (CBOW)
    
    $BigO = 1 \times 3.654 \times 10^9 \times 2653 = 9694062000000$
    
    