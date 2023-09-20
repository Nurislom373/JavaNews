# Java 21 New Features

## Sequenced Collection

Sequenced Collectionlar mavjud Java Collection class/interfacelariga qo'shilgan yangi xususiyat bo'lib, kutubxona
tomonidan taqdim etilgan standard methodlardan foydalangan holda uning birinchi va oxirgi elementiga kirish
imkonini beradi. Shuningdek, oddiy method bilan collectionning teskari yani reversed ko'rinishi olish imkonini beradi.

Ushbu yani JEP-431 yangilanishida collection hierarchyasida 3ta yangi interface taqdim etadi. Ushbu yangi
interfacelari **_SequencedCollection_** , **_SequencedSet_** va **_SequencedMap_**.

![seq_col](static/images/sequenced_collection.png)

Sequenced Interfacelarni qo'shilishidan maqsad collectionning birinchi va oxirgi elementlarni olishning oddiy methodlariga
bo'lgan talabdir. Java 21 dan oldin, Agar ArrayList birinchi va oxirgi elementlarni olishni istasak.

```java
var firstItem = arrayList.iterator().next(); 
var lastItem = arrayList.get(arrayList.size() - 1);
```

Yangi Sequenced collectionlar bilan biz oddiyroq methodlar yordamida xuddi shu narsani qilishimiz mumkin.

```java
var firstItem = arrayList.getFirst();
var lastItem = arrayList.getLast();
```
