# Autoencoder
## 實驗目的
利用mnist dataset訓練一個autoencoder，並觀察mnist在Encoder後的資料分佈
## 使用tensorflow設計一個 784x512x64x2x64x512x784的model
* loss = tf.reduce_mean(tf.pow(output_layer - y, 2))
* optimizer = tf.train.AdamOptimizer(learning_rate = 0.01).minimize(loss)
* activation function = sigmoid
* epochs = 50
* batch_size = 256
訓練loss：0.067 -> 0.046。
 ![](https://i.imgur.com/iXgmRrV.png)

Encoder 資料分佈：
![](https://i.imgur.com/nEFQ7x7.png)

## 改良
層數改為 784x128x10x2x10x128x784。
訓練loss：0.063 -> 0.039。
Encoder 資料分佈
![](https://i.imgur.com/PhIj9ox.png)

最大的改善在於Encoder最後一層有加入neuron = 10的全連接層，在看資料的時候會有預期中的分佈，而且參數更少效果也沒降。
要讓視覺話的資料呈現如預期，記得要對整體的架構做調整。

# CNN implement

2 CNN kernel size (5*5)&(3*3)， 3 DNN
Epoch 30/30
loss: 0.2765 - acc: 0.9012 - val_loss: 2.8119 - val_acc: 0.5434

dropout
Epoch 30/30
loss: 1.1552 - acc: 0.5949 - val_loss: 1.1832 - val_acc: 0.5858

BN
Epoch 30/30
loss: 0.3865 - acc: 0.8603 - val_loss: 1.6232 - val_acc: 0.6073

BN + Generator
Epoch 30/30
loss: 0.9063 - acc: 0.6766 - val_loss: 1.0477 - val_acc: 0.6449

dropout + BN
Epoch 30/30
loss: 0.4088 - acc: 0.8536 - val_loss: 1.4137 - val_acc: 0.6234

Dropout + BN + Generator
Epoch 30/30
loss: 0.8573 - acc: 0.6999 - val_loss: 0.8183 - val_acc: 0.7098

第一層改用3*3 kernel size, 差不多在15epoch就以收斂 
Epoch 30/30
loss: 0.9084 - acc: 0.6780 - val_loss: 1.0114 - val_acc: 0.6541

dropout BN 和 Generator 都能讓validation 的結果較好。
一起用時會發現最後一次epoch 的正確率不會是最好的，有train爆的情形。 