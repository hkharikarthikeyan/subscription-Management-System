from pydantic import BaseModel
from typing import Optional
from datetime import datetime

class UsageCreate(BaseModel):
    user_id: int
    subscription_id: int
    plan_id: int
    data_used: float
    user_status: str

class UserCreate(BaseModel):
    user_id: int
    name: str
    phone_no: str
    email: str

class PlanCreate(BaseModel):
    plan_id: int
    plan_name: str
    quota: int
    price: float
    plan_status: str
    discount: float

class RecommendationResponse(BaseModel):
    recommended_plan: dict
