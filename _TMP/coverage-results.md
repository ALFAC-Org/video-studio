
# Coverage Report: JaCoCo

* video-studio
      
      
| Outcome                 | Value                                                               |
|-------------------------|---------------------------------------------------------------------|
| Code Coverage %         | 51.26%               |
| :heavy_check_mark: Number of Lines Covered | 223    |
| :x: Number of Lines Missed  | 212     |
| Total Number of Lines   | 435     |


## Details:

    
### br/com/alfac/videostudio/infra/interceptor

<details>
    <summary>
:x: JwtAuthInterceptor.java
    </summary>

        
#### Lines Missed:
        
- Line #33
```
        } else {
```
</details>

    
### br/com/alfac/videostudio/infra/config/exception

<details>
    <summary>
:heavy_check_mark: ApiErrorItem.java
    </summary>

        
#### All Lines Covered!
        
</details>

    

<details>
    <summary>
:x: VideoStudioExceptionHandler.java
    </summary>

        
#### Lines Missed:
        
</details>

    

<details>
    <summary>
:x: ApiError.java
    </summary>

        
#### Lines Missed:
        
</details>

    
### br/com/alfac/videostudio/infra/handler

<details>
    <summary>
:x: UsuarioHandler.java
    </summary>

        
#### Lines Missed:
        
</details>

    

<details>
    <summary>
:heavy_check_mark: HealthCheckHandler.java
    </summary>

        
#### All Lines Covered!
        
</details>

    

<details>
    <summary>
:x: LoginHandler.java
    </summary>

        
#### Lines Missed:
        
</details>

    

<details>
    <summary>
:heavy_check_mark: GlobalExceptionHandler.java
    </summary>

        
#### All Lines Covered!
        
</details>

    

<details>
    <summary>
:x: VideoHandler.java
    </summary>

        
#### Lines Missed:
        
</details>

    
### br/com/alfac/videostudio/infra/dto

<details>
    <summary>
:x: VideoRequest.java
    </summary>

        
#### Lines Missed:
        
- Line #18
```
    }
```
</details>

    

<details>
    <summary>
:x: LoginRequest.java
    </summary>

        
#### Lines Missed:
        
- Line #19
```
    }
```
- Line #27
```
    }
```
</details>

    

<details>
    <summary>
:x: UsuarioRequest.java
    </summary>

        
#### Lines Missed:
        
- Line #24
```
    }
```
- Line #32
```
    }
```
</details>

    
### br/com/alfac/videostudio/infra/persistence

<details>
    <summary>
:x: UsuarioEntityRepository.java
    </summary>

        
</details>

    

<details>
    <summary>
:heavy_check_mark: VideoEntity.java
    </summary>

        
#### All Lines Covered!
        
</details>

    

<details>
    <summary>
:x: UsuarioEntity.java
    </summary>

        
#### Lines Missed:
        
- Line #28
```
    }
```
- Line #36
```
    }
```
- Line #44
```
    }
```
</details>

    

<details>
    <summary>
:x: VideoEntityRepository.java
    </summary>

        
</details>

    
### br/com/alfac/videostudio/infra/listener

<details>
    <summary>
:x: VideoProcessadoListener.java
    </summary>

        
#### Lines Missed:
        
- Line #13
```
    }
```
</details>

    
### br/com/alfac/videostudio/core/domain

<details>
    <summary>
:heavy_check_mark: Video.java
    </summary>

        
#### All Lines Covered!
        
</details>

    

<details>
    <summary>
:heavy_check_mark: StatusVideo.java
    </summary>

        
#### All Lines Covered!
        
</details>

    

<details>
    <summary>
:x: Usuario.java
    </summary>

        
#### Lines Missed:
        
- Line #17
```
    }
```
- Line #25
```
    }
```
- Line #33
```
    }
```
- Line #41
```
    }
```
</details>

    
### br/com/alfac/videostudio/core/application/dto

<details>
    <summary>
:x: UsuarioDTO.java
    </summary>

        
#### Lines Missed:
        
- Line #16
```
    }
```
- Line #24
```
    }
```
- Line #32
```
    }
```
</details>

    

<details>
    <summary>
:heavy_check_mark: VideoDTO.java
    </summary>

        
#### All Lines Covered!
        
</details>

    
### br/com/alfac/videostudio/core/application/adapters/controller

<details>
    <summary>
:x: ControladorUsuario.java
    </summary>

        
#### Lines Missed:
        
</details>

    

<details>
    <summary>
:heavy_check_mark: ControladorVideo.java
    </summary>

        
#### All Lines Covered!
        
</details>

    
### br/com/alfac/videostudio/infra/gateways

<details>
    <summary>
:x: RepositorioUsuarioGatewayImpl.java
    </summary>

        
#### Lines Missed:
        
</details>

    

<details>
    <summary>
:x: RepositorioVideoGatewayImpl.java
    </summary>

        
#### Lines Missed:
        
- Line #42
```
        }
```
</details>

    
### br/com/alfac/videostudio/core/application/adapters/presenter

<details>
    <summary>
:x: VideoPresenter.java
    </summary>

        
#### Lines Missed:
        
</details>

    

<details>
    <summary>
:x: UsuarioPresenter.java
    </summary>

        
#### Lines Missed:
        
</details>

    
### br/com/alfac/videostudio/core/application/adapters/gateways

<details>
    <summary>
:x: RepositorioVideoGateway.java
    </summary>

        
</details>

    

<details>
    <summary>
:x: RepositorioUsuarioGateway.java
    </summary>

        
</details>

    
### br/com/alfac/videostudio/infra/security

<details>
    <summary>
:x: JwtTokenProvider.java
    </summary>

        
#### Lines Missed:
        
- Line #24
```
                .sign(algorithm);
```
- Line #38
```
        } catch (Exception e) {
```
- Line #48
```
                .verify(token);
```
</details>

    
### br/com/alfac/videostudio/core/exception

<details>
    <summary>
:x: VideoStudioError.java
    </summary>

        
</details>

    

<details>
    <summary>
:x: VideoStudioErrosImpl.java
    </summary>

        
#### Lines Missed:
        
- Line #15
```
    }
```
- Line #22
```
    }
```
</details>

    

<details>
    <summary>
:x: StatusCode.java
    </summary>

        
#### Lines Missed:
        
- Line #11
```
    }
```
</details>

    

<details>
    <summary>
:x: VideoStudioException.java
    </summary>

        
#### Lines Missed:
        
- Line #14
```
    }
```
- Line #20
```
    }
```
- Line #26
```
    }
```
- Line #31
```
    }
```
- Line #38
```
    }
```
- Line #44
```
    }
```
</details>

    
### br/com/alfac/videostudio/core/exception/cliente

<details>
    <summary>
:x: VideoError.java
    </summary>

        
#### Lines Missed:
        
- Line #18
```
    }
```
- Line #22
```
    }
```
</details>

    
### br/com/alfac/videostudio/core/application/usecases

<details>
    <summary>
:x: CadastrarUsuarioUseCase.java
    </summary>

        
#### Lines Missed:
        
</details>

    

<details>
    <summary>
:heavy_check_mark: UploadVideoUseCase.java
    </summary>

        
#### All Lines Covered!
        
</details>

    

<details>
    <summary>
:x: ListarVideosUseCase.java
    </summary>

        
#### Lines Missed:
        
</details>

    
### br/com/alfac/videostudio/infra/mapper

<details>
    <summary>
:x: UsuarioEntityMapper.java
    </summary>

        
#### Lines Missed:
        
</details>

    

<details>
    <summary>
:x: UsuarioEntityMapperImpl.java
    </summary>

        
#### Lines Missed:
        
</details>

    

<details>
    <summary>
:heavy_check_mark: UsuarioMapper.java
    </summary>

        
#### All Lines Covered!
        
</details>

    

<details>
    <summary>
:x: VideoMapper.java
    </summary>

        
#### Lines Missed:
        
</details>

    

<details>
    <summary>
:x: VideoEntityMapperImpl.java
    </summary>

        
#### Lines Missed:
        
</details>

    

<details>
    <summary>
:heavy_check_mark: VideoEntityMapper.java
    </summary>

        
#### All Lines Covered!
        
</details>

    

<details>
    <summary>
:x: UsuarioMapperImpl.java
    </summary>

        
#### Lines Missed:
        
</details>

    

<details>
    <summary>
:x: VideoMapperImpl.java
    </summary>

        
#### Lines Missed:
        
</details>

    
