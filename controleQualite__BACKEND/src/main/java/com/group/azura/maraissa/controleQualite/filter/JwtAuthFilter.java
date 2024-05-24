package com.group.azura.maraissa.controleQualite.filter;

import com.group.azura.maraissa.controleQualite.service.AppUserDetailsService;
import com.group.azura.maraissa.controleQualite.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    final JwtService jwtService;
    final AppUserDetailsService userDetailsService;
    // final InternalExceptionHandler internalExceptionHandler;

    final CorsConfigurationSource corsConfigurationSource;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

            // Paths that should be excluded from JWT token checking
            List<String> pathsToExclude = Arrays.asList("/api/auth/authenticate", "/api/auth/refresh-token");
            String path = new UrlPathHelper().getPathWithinApplication(request);

            // If the request URL is in the paths to exclude, simply continue to the next
            // filter
            if (pathsToExclude.contains(path)) {
                filterChain.doFilter(request, response);
                return;
            }

            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtService.validateToken(token, userDetails)) {
                    log.info("Token is valid");
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    log.error("Token is invalid");

                }
            } else {

                log.error("SecurityContextHolder or username is invalid");

            }
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            //  internalExceptionHandler.handleException(response, ex);
        }
    }
}
