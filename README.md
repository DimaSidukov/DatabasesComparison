# DatabasesComparison

Implements several databases:
1) Room ☑️
2) SQLiteOpenHelper ☑️
3) Realm
4) ObjectBox

Which principle Room uses under the hood? Does it use MVCC (https://en.wikipedia.org/wiki/Multiversion_concurrency_control)?
What is DAO pattern in database? Any analogues?

Look up for Realm. He is considered to be quite efficient since it is written in C++

**Object-oriented database** vs **Object-relational database**

https://blog.mindorks.com/how-does-room-work-internally

Add functionality on the screen that allows performing different database operations, such as deleting, inserting, updating and querying data. Remove recyclerview list from the screen and replace with different buttons or something. Replace search field with "random words" button, that will send random word (either stored locally or fetched remotely) as query parameter. Добавить свертывающуюся стрелочку для операций в базе данных.

Android docs of data storage: https://developer.android.com/training/data-storage/room
