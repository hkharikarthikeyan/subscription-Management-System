from sqlalchemy import create_engine, MetaData, Table, Column, Integer, String, Float, Boolean, ForeignKey

DATABASE_URL = "sqlite:///./test.db"
engine = create_engine(DATABASE_URL)
metadata = MetaData()

users_usage = Table(
    "users_usage", metadata,
    Column("id", Integer, primary_key=True),
    Column("user_id", Integer),
    Column("subscription_id", Integer),
    Column("plan_id", Integer),
    Column("data_used", Float),
    Column("user_status", String),
)

user_details = Table(
    "user_details", metadata,
    Column("user_id", Integer, primary_key=True),
    Column("name", String),
    Column("phone_no", String),
    Column("email", String),
)

plan_info = Table(
    "plan_info", metadata,
    Column("plan_id", Integer, primary_key=True),
    Column("plan_name", String),
    Column("quota", Integer),
    Column("price", Float),
    Column("plan_status", String),
    Column("discount", Float),
)

metadata.create_all(engine)
