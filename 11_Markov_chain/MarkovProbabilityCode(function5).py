import pytest
from enum import Enum
from typing import Tuple, List
import tempfile

class ExportFormat(Enum):
    PNG = ("png_option.png", "output.png")
    PDF = ("pdf_option.png", "output.pdf")
    SVG = ("svg_option.png", "output.svg")

# 定义所有要测试的路径组合
TEST_PATHS = [
    (ExportFormat.PNG, True),   # S0→S1→S2→S5→S6→S0
    (ExportFormat.PDF, False),  # S0→S1→S3→S5→S0 (取消)
    (ExportFormat.SVG, True),   # S0→S1→S4→S5→S6→S0
]

@pytest.mark.parametrize("format,should_save", TEST_PATHS)
def test_export_path(format: ExportFormat, should_save: bool, tmp_path):
    """并行测试不同导出路径"""
    import pyautogui
    from PIL import Image
    
    # 1. 启动Freeplane
    proc = subprocess.Popen(["freeplane.exe", "test.mm"])
    
    try:
        # 2. 执行导出操作链
        pyautogui.click(pyautogui.locateOnScreen('export_menu.png'))  # S0→S1
        pyautogui.click(pyautogui.locateOnScreen(format.value[0]))    # S1→S2/S3/S4
        
        if should_save:
            # 保存路径
            save_path = tmp_path / format.value[1]
            pyautogui.typewrite(str(save_path))
            pyautogui.press('enter')  # S5→S6
            assert save_path.exists()
            
            # 文件验证
            if format == ExportFormat.PNG:
                with Image.open(save_path) as img:
                    assert img.size > (0, 0)
            elif format == ExportFormat.PDF:
                from pdfminer.high_level import extract_text
                assert len(extract_text(save_path)) > 0
        else:
            # 取消路径
            pyautogui.press('esc')  # S5→S0
            assert not (tmp_path / format.value[1]).exists()
            
    finally:
        proc.terminate()