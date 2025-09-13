from database import engine, users_usage, user_details, plan_info
from sqlalchemy import insert, select
import pandas as pd

async def create_usage_record(data):
    query = insert(users_usage).values(**data)
    with engine.connect() as conn:
        conn.execute(query)

async def create_user(data):
    query = insert(user_details).values(**data)
    with engine.connect() as conn:
        conn.execute(query)

async def create_plan(data):
    query = insert(plan_info).values(**data)
    with engine.connect() as conn:
        conn.execute(query)

async def get_user_usage(subscription_id: int, user_id: int):
    query = select(users_usage).where(
        (users_usage.c.subscription_id == subscription_id) &
        (users_usage.c.user_id == user_id)
    )
    with engine.connect() as conn:
        result = conn.execute(query)
        rows = result.fetchall()
        return pd.DataFrame([dict(row) for row in rows])

async def get_plan(plan_id: int):
    query = select(plan_info).where(plan_info.c.plan_id == plan_id)
    with engine.connect() as conn:
        result = conn.execute(query).fetchone()
        return dict(result) if result else None

async def get_all_plans():
    query = select(plan_info)
    with engine.connect() as conn:
        result = conn.execute(query)
        return [dict(row) for row in result]
