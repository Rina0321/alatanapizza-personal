SET names utf8;
SET foreign_key_checks = 0;
DROP DATABASE IF EXISTS alatanapizza;
CREATE DATABASE IF NOT EXISTS alatanapizza;

use alatanapizza;

-- -----------↓会員情報テーブル↓----------------------------
CREATE TABLE user_info(
id int PRIMARY KEY NOT NULL AUTO_INCREMENT,-- ID
user_id varchar(16) UNIQUE KEY NOT NULL,-- ユーザーID
password varchar(16) NOT NULL,-- パスワード
family_name varchar(32) NOT NULL, -- 姓
first_name varchar(32) NOT NULL,-- 名
family_name_kana varchar(32) NOT NULL,-- 姓かな
first_name_kana varchar(32) NOT NULL, -- 名かな
sex tinyint NOT NULL DEFAULT 0, -- 性別 (0:男性 1:女性)
email varchar(32) NOT NULL,-- メールアドレス
secret_question int NOT NULL, -- 秘密の質問 (1:好きな食べ物 2:嫌いな食べ物)
secret_answer varchar(32)  NOT NULL, -- 秘密の質問の答え
status tinyint NOT NULL DEFAULT 1,-- ステータス (0:無効、1:有効)
logined tinyint NOT NULL DEFAULT 0,-- ログインフラグ (0:未ログイン、1:ログイン済み)
regist_date datetime NOT NULL,-- 登録日
update_date datetime-- 更新日
);


-- -----------↓カテゴリマスタテーブル↓----------------------------
CREATE TABLE m_category(
id int PRIMARY KEY NOT NULL AUTO_INCREMENT, -- ID
category_id int NOT NULL UNIQUE KEY, -- カテゴリID
category_name varchar(20) NOT NULL UNIQUE KEY, -- カテゴリ名
category_description varchar(100), -- カテゴリ詳細
insert_date datetime NOT NULL, -- 登録日
update_date datetime-- 更新日
);


-- -----------↓商品情報テーブル↓----------------------------
CREATE TABLE product_info(
id int PRIMARY KEY NOT NULL AUTO_INCREMENT,-- ID
product_id int UNIQUE KEY NOT NULL,-- 商品ID
product_name varchar(100) UNIQUE KEY NOT NULL,-- 商品名
product_name_kana varchar(100) UNIQUE KEY NOT NULL,-- 商品名かな
product_description varchar(255) NOT NULL,-- 商品詳細
category_id int NOT NULL,-- カテゴリID
msize_price int,-- Mサイズ価格
lsize_price int,-- Lサイズ価格
price int,-- サイドメニュー、ドリンク価格
stock int,-- 在庫
image_file_path varchar(100),-- 画像ファイルパス
image_file_name varchar(100),-- 画像ファイル名
release_date datetime NOT NULL, -- 発売年月
release_company varchar(50), -- 発売会社
status tinyint NOT NULL DEFAULT 0,-- ステータス(0:無効、1:有効)
regist_date datetime NOT NULL,-- 登録日
update_date datetime,-- 更新日
FOREIGN KEY(category_id) REFERENCES m_category(category_id) ON UPDATE CASCADE
);


-- -----------↓トッピング情報テーブル↓----------------------------
CREATE TABLE m_topping(
id int PRIMARY KEY NOT NULL AUTO_INCREMENT, -- ID
topping_id int NOT NULL UNIQUE KEY, -- カテゴリID
topping_name varchar(20) NOT NULL UNIQUE KEY, -- トッピング名
msize_price int,-- Mサイズ価格
lsize_price int,-- Lサイズ価格
insert_date datetime NOT NULL, -- 登録日
update_date datetime-- 更新日
);


-- -----------↓カート情報テーブル↓----------------------------
CREATE TABLE cart_info(
id int PRIMARY KEY NOT NULL AUTO_INCREMENT,-- ID
user_id varchar(16) NOT NULL,-- ユーザーID
temp_user_id varchar(128),-- 仮ユーザーID
product_id int NOT NULL ,-- 商品ID
product_count int NOT NULL,-- 個数
price int NOT NULL,-- 金額
regist_date datetime NOT NULL,-- 登録日
update_date datetime,-- 更新日
/*FOREIGN KEY(user_id) REFERENCES user_info(user_id) ON UPDATE CASCADE,*/
FOREIGN KEY(product_id) REFERENCES product_info(product_id) ON UPDATE CASCADE
);

-- -----------↓お気に入り情報テーブル↓----------------------------
CREATE TABLE favorite_info(
id int not null primary key auto_increment,
user_id varchar (16),
product_id int,
regist_date datetime,
unique(user_id,product_id)

);

-- -----------↓購入履歴情報テーブル↓----------------------------
CREATE TABLE purchase_history_info(
id int PRIMARY KEY NOT NULL AUTO_INCREMENT,-- ID
user_id varchar(16) NOT NULL ,-- ユーザーID
product_id int NOT NULL ,-- 商品ID
product_count int NOT NULL ,-- 個数
price int NOT NULL, -- 金額
regist_date datetime NOT NULL,-- 登録日
update_date datetime,-- 更新日
FOREIGN KEY(user_id) REFERENCES user_info(user_id) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY(product_id) REFERENCES product_info(product_id) ON UPDATE CASCADE
);


-- -----------↓宛先情報テーブル↓-----------------------------------
CREATE TABLE destination_info(
id int PRIMARY KEY NOT NULL AUTO_INCREMENT,-- ID
user_id varchar(16) NOT NULL,-- ユーザーID
family_name varchar(32) NOT NULL,-- 姓
first_name varchar(32) NOT NULL,-- 名
family_name_kana varchar(32) NOT NULL,-- 姓かな
first_name_kana varchar(32) NOT NULL,-- 名かな
email varchar(32) NOT NULL,-- メールアドレス
tel_number varchar(13) NOT NULL,-- 電話番号
user_address varchar(50) NOT NULL,-- 住所
regist_date datetime NOT NULL,-- 登録日
update_date datetime-- 更新日
);




-- ---------------↓INSERT文↓--------------------------------------

INSERT INTO user_info(-- ------会員情報テーブルへ-----------------
	user_id,-- ユーザーID
	password,-- パスワード
	family_name,-- 姓
	first_name,-- 名
	family_name_kana,-- 姓かな
	first_name_kana, -- 名かな
	sex,-- 性別 (0:男性 1:女性)
	email,-- メールアドレス
	secret_question,-- 秘密の質問 (1:好きな食べ物 2:嫌いな食べ物)
	secret_answer,-- 秘密の質問の答え
	regist_date -- 登録日
)VALUES(
	"taro",-- ユーザーID
	"123",-- パスワード
	"ポンデ",-- 姓
	"ライオン",-- 名
	"ぽんで",-- 姓かな
	"らいおん", -- 名かな
	0,-- 性別 (0:男性 1:女性)
	"ponde@com",-- メールアドレス
	"1",-- 秘密の質問 (1:好きな食べ物 2:嫌いな食べ物)
	"いちご",-- 秘密の質問の答え
	NOW()-- 登録日
);

INSERT INTO destination_info(-- -----------宛先情報テーブルへ-----------
	user_id,-- ユーザーID
	family_name,-- 姓
	first_name,-- 名
	family_name_kana,-- 姓かな
	first_name_kana,-- 名かな
	email,-- メールアドレス
	tel_number,-- 電話番号
	user_address,-- 住所
	regist_date-- 登録日
)VALUES(
	"taro",-- ユーザーID
	"ポンデ",-- 姓
	"ライオン",-- 名
	"ぽんで",-- 姓かな
	"らいおん", -- 名かな
	"ponde@com",-- メールアドレス
	"080-0000-0000",-- 電話番号
	"東京",-- 住所
	NOW()-- 登録日
);


INSERT INTO m_category(-- --------カテゴリーマスタテーブルへ-----------
	category_id,-- カテゴリID
	category_name,-- カテゴリ名
	category_description,-- カテゴリ詳細
	insert_date,-- 登録日
	update_date-- 更新日
)VALUES
	(1,
	"全てのカテゴリー",
	"全てのカテゴリー",
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),

	(2,
	"ピザ",
	"ピザに関するカテゴリー",
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),

    (3,
	"サイドメニュー",
	"サイドメニューに関するカテゴリー",
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),

    (4,
	"ドリンク",
	"ドリンクに関するカテゴリー",
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	);


    INSERT INTO product_info(-- ------商品情報テーブルへ--------------
	product_id,-- 商品ID
    product_name,-- 商品名
    product_name_kana,-- 商品名かな
    product_description,-- 商品詳細
    category_id,-- カテゴリID
    msize_price,-- Mサイズ値段
    lsize_price,-- Lサイズ値段
    price,-- サイドメニュー、ドリンク価格
    stock,-- 在庫
    image_file_path, -- 画像ファイルパス
	image_file_name, -- 画像ファイル名
	release_date,-- 発売年月
	release_company,-- 発売会社
	status,-- ステータス(0:無効、1:有効)
	regist_date,-- 登録日
	update_date-- 更新日
)VALUES(-- ---------------マルゲリータ-----------------------
	1,-- 商品ID
	"Margherita",-- 商品名
	"まるげりーた",-- 商品名かな
	"トマトソースとチーズを使用", -- 商品詳細
	2,-- カテゴリID
	2000, -- Mサイズ値段
    2500, -- Lサイズ値段
    0,
	10,-- 在庫
	"./images/pizza/Margherita.png",-- 画像ファイルパス
	"Margherita.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- ----------------シーフードミックス------------------
    2,-- 商品ID
	"SeafoodMix",-- 商品名
	"しーふーどみっくす",-- 商品名かな
	"シーフードを使用", -- 商品詳細
	2,-- カテゴリID
	2000, -- Mサイズ値段
    2500, -- Lサイズ値段
    0,
	10,-- 在庫
	"./images/pizza/SeafoodMix.png",-- 画像ファイルパス
	"SeafoodMix.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- ----------------デラックスピザ-----------------------
    3,-- 商品ID
	"DeluxePizza",-- 商品名
	"でらっくすぴざ",-- 商品名かな
	"トマトソースを使用", -- 商品詳細
	2,-- カテゴリID
	2000, -- Mサイズ値段
    2500, -- Lサイズ値段
    0,
	10,-- 在庫
	"./images/pizza/DeluxePizza.png",-- 画像ファイルパス
	"DeluxePizza,png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- --------------------チーズアンドチーズ----------------
    4,-- 商品ID
	"CheeseAndCheese",-- 商品名
	"ちーずあんどちーず",-- 商品名かな
	"チーズを使用", -- 商品詳細
	2,-- カテゴリID
	2000, -- Mサイズ値段
    2500, -- Lサイズ値段
    0,
	10,-- 在庫
	"./images/pizza/CheeseAndCheese.png",-- 画像ファイルパス
	"CheeseAndCheese.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- -------------------エビマヨベーコン--------------------
    5,-- 商品ID
	"EbimayoBacon",-- 商品名
	"えびまよべーこん",-- 商品名かな
	"シーフードを使用", -- 商品詳細
	2,-- カテゴリID
	2000, -- Mサイズ値段
    2500, -- Lサイズ値段
    0,
	10,-- 在庫
	"./images/pizza/Ebimayo.png",-- 画像ファイルパス
	"Ebimayo.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- --------------------ツナチーズ-------------------------
    6,-- 商品ID
	"TunaCheese",-- 商品名
	"つなちーず",-- 商品名かな
	"シーフードとチーズを使用", -- 商品詳細
	2,-- カテゴリID
	2000, -- Mサイズ値段
    2500, -- Lサイズ値段
    0,
	10,-- 在庫
	"./images/pizza/TunaCheese.png",-- 画像ファイルパス
	"TunaCheese.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- -------------------地中海の恵み--------------------------
    7,-- 商品ID
	"Mediterranean",-- 商品名
	"ちちゅうかいのめぐみ",-- 商品名かな
	"トマトソースとシーフードを使用", -- 商品詳細
	2,-- カテゴリID
	2000, -- Mサイズ値段
    2500, -- Lサイズ値段
    0,
	10,-- 在庫
	"./images/pizza/Mediterranean.png",-- 画像ファイルパス
	"Mediterranean.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- -----------------バジルピザ-----------------------------
    8,-- 商品ID
	"BasilPizza",-- 商品名
	"ばじるぴざ",-- 商品名かな
	"バジルを使用", -- 商品詳細
	2,-- カテゴリID
	2000, -- Mサイズ値段
    2500, -- Lサイズ値段
    0,
	10,-- 在庫
	"./images/pizza/BasilPizza.png",-- 画像ファイルパス
	"BasilPizza.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- -----------------------てりやきピザ----------------------
    9,-- 商品ID
	"TeriyakiPizza",-- 商品名
	"てりやきぴざ",-- 商品名かな
	"和風、てりやきを使用", -- 商品詳細
	2,-- カテゴリID
	2000, -- Mサイズ値段
    2500, -- Lサイズ値段
    0,
	10,-- 在庫
	"./images/pizza/TeriyakiPizza.png",-- 画像ファイルパス
	"TeriyakiPizza.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- ---------------------フライドチキン------------------------
    10,-- 商品ID
	"FriedChicken",-- 商品名
	"フライドチキン",-- 商品名かな
	"カリカリ！サイドメニュー定番", -- 商品詳細
	3,-- カテゴリID
	0,
    0,
    520,-- 価格
	30,-- 在庫
	"./images/side/FriedChicken.png",-- 画像ファイルパス
	"FriedChicken.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- ---------------------フライドポテト------------------------
    11,-- 商品ID
	"FriedPotato",-- 商品名
	"フライポテト",-- 商品名かな
	"カリカリ！サイドメニュー定番", -- 商品詳細
	3,-- カテゴリID
	0,
    0,
    480,-- 価格
	30,-- 在庫
	"./images/side/FriedPotato.png",-- 画像ファイルパス
	"FriedPotato.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- ---------------------シーザーサラダ------------------------
    12,-- 商品ID
	"CaesarSalad",-- 商品名
	"しーざーさらだ",-- 商品名かな
	"ドレッシングが美味しい", -- 商品詳細
	3,-- カテゴリID
	0,
    0,
    620,-- 価格
	30,-- 在庫
	"./images/side/CaesarSalad.png",-- 画像ファイルパス
	"CaesarSalad.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- ---------------------グラタン----------------------------
    13,-- 商品ID
	"Gratin",-- 商品名
	"ぐらたん",-- 商品名かな
	"かまどでじっくり、ホワイトソースは絶品！", -- 商品詳細
	3,-- カテゴリID
	0,
    0,
    680,-- 価格
	10,-- 在庫
	"./images/side/Gratin.png",-- 画像ファイルパス
	"Gratin.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- ---------------------コーンスープ------------------------
    14,-- 商品ID
	"CornSoup",-- 商品名
	"こーんすーぷ",-- 商品名かな
	"コーンの甘みをぎゅっと！", -- 商品詳細
	3,-- カテゴリID
	0,
    0,
    500,-- 価格
	30,-- 在庫
	"./images/side/CornSoup.png",-- 画像ファイルパス
	"CornSoup.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- ---------------------アイス----------------------------
    15,-- 商品ID
	"Ice",-- 商品名
	"あいす",-- 商品名かな
	"ひんやり！サイドメニュー定番", -- 商品詳細
	3,-- カテゴリID
	0,
    0,
    350,-- 価格
	30,-- 在庫
	"./images/side/Ice.png",-- 画像ファイルパス
	"Ice.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- ---------------------コーラ----------------------------
    16,-- 商品ID
	"Cola",-- 商品名
	"こーら",-- 商品名かな
	"ピザに良く合う！", -- 商品詳細
	4,-- カテゴリID
	0,
    0,
    100,-- 価格
	50,-- 在庫
	"./images/drink/Cola.png",-- 画像ファイルパス
	"Cola.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- ---------------------ウーロン茶------------------------
    17,-- 商品ID
	"OolongTea",-- 商品名
	"うーろんちゃ",-- 商品名かな
	"ノンカフェインです", -- 商品詳細
	4,-- カテゴリID
	0,
    0,
    100,-- 価格
	50,-- 在庫
	"./images/drink/OolongTea.png",-- 画像ファイルパス
	" OolongTea.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	),(-- ---------------------オレンジジュース--------------------
    18,-- 商品ID
	"OrangeJuice",-- 商品名
	"おれんじじゅーす",-- 商品名かな
	"果汁100％", -- 商品詳細
	4,-- カテゴリID
	0,
    0,
    100,-- 価格
	50,-- 在庫
	"./images/drink/OrangeJuice.png",-- 画像ファイルパス
	"OrangeJuice.png",-- 画像ファイル名
	now(),-- 発売年月
	"ALATANAPIZZA",-- 発売会社
	1,-- ステータス(0:無効、1:有効)
	NOW(),-- 登録日
	NOW()-- 更新日
	);


    INSERT INTO m_topping(-- --------トッピングマスタテーブルへ-----------
	topping_id,-- トッピングID
	topping_name,-- トッピング名
    msize_price,-- Mサイズ値段
    lsize_price,-- Lサイズ値段
	insert_date,-- 登録日
	update_date-- 更新日
)VALUES
	(1,
	"トマト",
	324,
    486,
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),
    (2,
	"エビ",
	324,
    486,
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),
    (3,
	"オニオン",
	324,
    486,
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),
    (4,
	"コーン",
	324,
    486,
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),
    (5,
	"ピーマン",
	324,
    486,
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),
    (6,
	"チーズ",
	324,
    486,
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),
    (7,
	"アボカド",
	324,
    486,
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),
    (8,
	"ガーリック",
	324,
    486,
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),
    (9,
	"ベーコン",
	324,
    486,
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),
    (10,
	"オリーブ",
	324,
    486,
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),
    (11,
	"サラミ",
	324,
    486,
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	),
    (12,
	"マッシュルーム",
	324,
    486,
	cast('2018-03-01' as date),
	cast('2018-03-01' as date)
	);
