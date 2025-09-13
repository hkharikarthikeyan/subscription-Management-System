from fastapi import FastAPI
from schemas import UsageCreate, UserCreate, PlanCreate, RecommendationResponse
from crud import create_usage_record, create_user, create_plan, get_user_usage
from recommendation import recommend_plan

app = FastAPI()

@app.post("/usage/")
async def record_usage(usage: UsageCreate):
    await create_usage_record(usage.dict())
    return {"message": "Usage recorded successfully"}

@app.post("/user/")
async def create_user_profile(user: UserCreate):
    await create_user(user.dict())
    return {"message": "User created successfully"}

@app.post("/plan/")
async def create_subscription_plan(plan: PlanCreate):
    await create_plan(plan.dict())
    return {"message": "Plan created successfully"}

@app.get("/recommendation/{user_id}/{subscription_id}", response_model=RecommendationResponse)
async def get_recommendation(user_id: int, subscription_id: int):
    usage_df = await get_user_usage(subscription_id, user_id)
    if usage_df.empty:
        return {"recommended_plan": {"message": "Not enough data"}}

    current_plan_id = usage_df.iloc[-1]['plan_id']
    recommendation = recommend_plan(usage_df, current_plan_id)
    return recommendation