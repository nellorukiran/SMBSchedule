@echo off
setlocal
set LogLocation=D:\Application\Reports\Logs
for /f "tokens=1-8 delims=.:/-, " %%i in ('echo exit^|cmd /q /k"prompt $D $T"') do (
   for /f "tokens=2-4 skip=1 delims=/-,()" %%a in ('echo.^|date') do (
set dow=%%i
set %%a=%%j
set %%b=%%k
set %%c=%%l
set hh=%%m
set min=%%n
set sec=%%o
set hsec=%%p
)
)
endlocal & set timeStamp=%dd%-%mm%-%yy%_%hh%-%min%-%sec%
@echo ************** Started SMB Address Job ********************
java -jar D:\Application\Reports\Address.jar >>D:\Application\Reports\Logs\%timeStamp%.log
@echo ************** End SMB Address Job  ********************

@echo ************** Started SMB Customer Id Job ********************
java -jar D:\Application\Reports\CustomerId.jar >>D:\Application\Reports\Logs\%timeStamp%.log
@echo ************** End SMB Customer Id Job  ********************




exit



