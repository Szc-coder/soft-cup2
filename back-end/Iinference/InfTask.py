from typing import List


class Task:
    # 0目标检测、1目标提取、2变化检测、3地物分类
    # 文件命名：用户id+任务类型+任务时间
    taskId: int
    fileLocation: List[str]
    # 0:zip, 1:files 在线选取与从文件读取原理一致
    mode: int
    uuid: str
