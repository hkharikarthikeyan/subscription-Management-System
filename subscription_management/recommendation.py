from typing import Dict, Any
import pandas as pd
from database import engine, plan_info
from sqlalchemy import select

def recommend_plan(user_usage: pd.DataFrame, current_plan_id: int) -> Dict[str, Any]:
    avg_usage = user_usage['data_used'].mean()

    with engine.connect() as conn:
        current_plan = conn.execute(select(plan_info).where(plan_info.c.plan_id == current_plan_id)).fetchone()
        current_quota = current_plan['quota']

        plans = [dict(row) for row in conn.execute(select(plan_info).order_by(plan_info.c.quota.asc()))]

    if avg_usage >= 0.8 * current_quota:
        for plan in plans:
            if plan['quota'] > current_quota and plan['plan_status'] == 'active':
                return {"recommended_plan": plan}

    current_plan_data = next((plan for plan in plans if plan['plan_id'] == current_plan_id), {})
    return {"recommended_plan": current_plan_data}
